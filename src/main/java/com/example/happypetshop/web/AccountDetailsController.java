package com.example.happypetshop.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountDetailsController {

    @GetMapping("/user/details")
    public String userDetails(){

        return "details";
    }

}
