package cn.zhl.springbootsecuritywebsample.securityconfig;

import cn.zhl.springbootsecuritywebsample.model.SysUser;
import cn.zhl.springbootsecuritywebsample.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private HttpSession session;

    @Autowired
    private IUserService userService;

    /**
     * Validate user info is correct form database
     *
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        // 检查用户名密码是否正确
        SysUser user = userService.getUserByUserName(username);
        if (user == null) {
            logger.error("{} login failed, username or password is wrong", username);
            throw new BadCredentialsException("Username or password is not correct");
        }

        // 用户信息有效时将其放入 session 中
        session.setAttribute("user", user);
        Authentication auth = new UsernamePasswordAuthenticationToken(username, password, grantedAuthorities);
        return auth;
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}