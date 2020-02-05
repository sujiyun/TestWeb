package com.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/register")
    public String userRegister() {
        return "/user/register";
    }

    @GetMapping("/login")
    public RedirectView login(@RequestParam String userId, @RequestParam String userLoginId, @RequestParam String userName, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("userId", userId);
        session.setAttribute("userName", userName);

        return new RedirectView( "/board/list");
    }

    @GetMapping("/logout")
    public RedirectView logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return new RedirectView( "/");
    }

}
