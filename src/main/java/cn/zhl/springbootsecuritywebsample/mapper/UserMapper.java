package cn.zhl.springbootsecuritywebsample.mapper;

import cn.zhl.springbootsecuritywebsample.model.SysUser;

public interface UserMapper {

    SysUser getUserByUserName(String userName);

}
