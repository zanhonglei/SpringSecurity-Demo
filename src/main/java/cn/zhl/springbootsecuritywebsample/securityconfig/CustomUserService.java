package cn.zhl.springbootsecuritywebsample.securityconfig;

import cn.zhl.springbootsecuritywebsample.model.SysUser;
import cn.zhl.springbootsecuritywebsample.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserService implements UserDetailsService {

    @Autowired
    private IUserService userServiceImpl;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        SysUser sysUser = userServiceImpl.getUserByUserName(userName);
        if (sysUser == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        return sysUser;
    }
}
