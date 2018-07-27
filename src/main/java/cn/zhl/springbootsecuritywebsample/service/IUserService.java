package cn.zhl.springbootsecuritywebsample.service;

import cn.zhl.springbootsecuritywebsample.model.SysUser;

public interface IUserService {
    public SysUser getUserByUserName(String userName);
}
