package com.example.smartpensionsystem.service.impl;

import com.example.smartpensionsystem.entity.SysUser;
import com.example.smartpensionsystem.mapper.SysUserMapper;
import com.example.smartpensionsystem.service.SysUserService;
import com.example.smartpensionsystem.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser findByUserName(String username) {
        return sysUserMapper.findByUserName(username);
    }

    @Override
    public void register(String username, String password) {
        password= Md5Util.getMD5String(password);
        sysUserMapper.addSysUser(username,password);
    }

    @Override
    public void updatePwd(String username, String newPwd) {
        newPwd= Md5Util.getMD5String(newPwd);
        sysUserMapper.updatePwd(username,newPwd);
    }

    @Override
    public void updateInfo(SysUser sysUser) {
        sysUserMapper.updateInfo(sysUser);
    }

    @Override
    public List<SysUser> getAllAdmins(){return sysUserMapper.getAllAdmins();}

}
