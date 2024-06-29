package com.example.smartpensionsystem.mapper;

import com.example.smartpensionsystem.entity.SysUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface SysUserMapper {

    @Select("select * from sys_user where username =#{username}")
    SysUser findByUserName(String username);


    @Insert("insert into sys_user(username,password) values(#{username},#{password})")
    void addSysUser(String username, String password);


    @Update("update sys_user set password=#{newPwd} where username=#{username}")
    void updatePwd(String username, String newPwd);


    @Update("update sys_user set real_name=#{real_name},sex=#{sex},email=#{email},phone=#{phone},description=#{description} where username=#{username}")
    void updateInfo(SysUser sysUser);
}
