package com.springboot.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class SsoController {
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
}
