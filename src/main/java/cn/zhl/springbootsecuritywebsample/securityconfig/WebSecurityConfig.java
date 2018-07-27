package cn.zhl.springbootsecuritywebsample.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean
    UserDetailsService customUserService(){
        return new CustomUserService();
    }

    AuthenticationProvider customAuthenticationProvider() {
        return new CustomAuthenticationProvider();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                //.failureUrl("/login-error.html").permitAll()
                .defaultSuccessUrl("/");
                /*.and()
                .logout();*/


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService()).passwordEncoder(passwordEncoder());//and().authenticationProvider(customAuthenticationProvider());

        // 使用自定义的 Authentication Provider
        //auth.authenticationProvider(customAuthenticationProvider());
    }

    /*@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }*/

    @Bean
     public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
