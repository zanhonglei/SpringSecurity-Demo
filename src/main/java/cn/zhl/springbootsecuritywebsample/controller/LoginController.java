package cn.zhl.springbootsecuritywebsample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @GetMapping("/login")
    public String login() {
        logger.info("/login----------->");
        return "login";
    }
    @GetMapping("/logout")
    public String logout() {
        logger.info("/logout----------->");
        return "redirect:/login";
    }
}
