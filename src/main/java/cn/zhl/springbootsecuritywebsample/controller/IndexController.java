package cn.zhl.springbootsecuritywebsample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index() {
        return "index";
    }
    @GetMapping("/403")
    public String to403() {
        return "403";
    }
}
