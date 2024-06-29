package com.example.smartpensionsystem.controller;

import com.example.smartpensionsystem.entity.Result;
import com.example.smartpensionsystem.entity.SysUser;
import com.example.smartpensionsystem.service.SysUserService;
import com.example.smartpensionsystem.utils.JwtUtil;
import com.example.smartpensionsystem.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    // 需要以json格式传入username和password
    @PostMapping("/register")
    public Result register(@RequestBody Map<String,String> params){
        // 查询用户
        SysUser u=sysUserService.findByUserName(params.get("username"));
        if(u==null) {
            if(params.get("username")==null){
                return Result.error("用户名不能为空");
            }else if(params.get("password")==null){
                return Result.error("密码不能为空");
            }
            // 注册
            sysUserService.register(params.get("username"),params.get("password"));
            return Result.success();
        }else {
            // 占用
            return Result.error("用户名已被占用");
        }
    }

    // 需要以json格式传入username和password
    @PostMapping("/login")
    public Result<String> login(@RequestBody Map<String,String> params){
        // 根据用户名查询用户
        SysUser u=sysUserService.findByUserName(params.get("username"));
        // 如果用户名或密码不对
        if(u!=null&& Md5Util.getMD5String(params.get("password")).equals(u.getPassword())){
            // 登录成功返回jwt令牌
            Map<String,Object>claims=new HashMap<>();
            claims.put("id",u.getId());
            claims.put("username",u.getUsername());
            String  token= JwtUtil.genToken(claims);
            // token存储到redis中
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            operations.set(token,token,1, TimeUnit.HOURS);
            return Result.success(token);
        }else{
            return Result.error("用户名或密码错误");
        }
    }


    // 更新用户密码,需要以json格式传入old_pwd和new_pwd
    @PatchMapping("/user/{username}/password")
    public Result updatePsw(@PathVariable String username, @RequestBody Map<String,String> params){
        String oldPwd=params.get("old_pwd");
        String newPwd=params.get("new_pwd");

        if(!StringUtils.hasLength(oldPwd)||!StringUtils.hasLength(newPwd)||StringUtils.hasLength(username)){
            Result.error("缺少必要参数");
        }

        // 原密码是否正确
        // 调用sysUserService根据用户名拿到原密码，再和old_pws
        SysUser user=sysUserService.findByUserName(username);

        if(!user.getPassword().equals(Md5Util.getMD5String(oldPwd))){
            return Result.error("原密码错误");
        }

        sysUserService.updatePwd(username,newPwd);
        // 删除redis中对应的token
        // ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        // operations.getOperations().delete(token);
        return Result.success();
    }

    // 根据用户名获取用户信息
    @GetMapping("/user/{username}")
    public Result findByUsername(@PathVariable String username){
        // 根据用户名查询用户
        SysUser u=sysUserService.findByUserName(username);
        if(u==null){
            return Result.error("用户不存在");
        }else{
            return Result.success(u);
        }
    }

    // 修改用户信息
    @PutMapping("/user/{username}")
    public Result updateSysUserInfo(@PathVariable String username,@RequestBody Map<String,String> params){
        SysUser sysUser=new SysUser();
        sysUser.setUsername(username);
        sysUser.setReal_name(params.get("real_name"));
        sysUser.setSex(params.get("sex"));
        sysUser.setEmail(params.get("email"));
        sysUser.setPhone(params.get("phone"));
        sysUser.setDescription(params.get("description"));
        sysUserService.updateInfo(sysUser);
        return Result.success();
    }
}
