package com.eNote.eNote_core.controllers;

import com.eNote.eNote_core.security.AccountDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Alterranius
 */
@Controller
public class HelloController {
    @GetMapping("/auth/login")
    public String sayHello() {
        return "/hello";
    }

    @GetMapping("/showUserInfo")
    @ResponseBody
    public String showUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AccountDetails personDetails = (AccountDetails) authentication.getPrincipal();
        System.out.println(personDetails.getAccountData());
        return "redirect:/hello";
    }
}
