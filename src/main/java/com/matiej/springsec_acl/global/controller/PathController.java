package com.matiej.springsec_acl.global.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PathController {
    //

     @RequestMapping("/login")
     public String list() {
     return "loginPage";
     }

    @RequestMapping("/")
    public String home() {
        return "redirect:/login";
    }
}
