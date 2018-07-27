package cn.zhl.springbootsecuritywebsample.service.impl;

import cn.zhl.springbootsecuritywebsample.model.SysUser;
import cn.zhl.springbootsecuritywebsample.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public SysUser getUserByUserName(String userName) {
        logger.info(this.getClass().getName()+ "--------->"+ userName);
        if ("admin".equals(userName)) {
            SysUser sysUser = new SysUser();
            sysUser.setUserId("1");
            sysUser.setPassword(new BCryptPasswordEncoder().encode("123456"));
            sysUser.setUserId("admin");
            return sysUser;
        }
        return null;
    }

}
