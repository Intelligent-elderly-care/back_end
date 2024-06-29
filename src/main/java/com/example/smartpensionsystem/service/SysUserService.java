package com.example.smartpensionsystem.service;

import com.example.smartpensionsystem.entity.SysUser;

public interface SysUserService {
    // 注册
    void register(String username, String password);

    // 根据用户名查询用户
    SysUser findByUserName(String username);

    // 更新密码
    void updatePwd(String username,String newPwd);

    // 更新管理员信息
    void updateInfo(SysUser sysUser);
}
