package com.roytuts.spring.boot.security.form.based.jdbc.userdetailsservice.auth.controller;

import com.roytuts.spring.boot.security.form.based.jdbc.userdetailsservice.auth.dao.UserDao;
import com.roytuts.spring.boot.security.form.based.jdbc.userdetailsservice.auth.model.User;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SpringSecurityController {

    @Autowired
    private UserDao userDao;

    @GetMapping("/newuser")
    public String newuser(Model model) {
        model.addAttribute("title", "New User");
        model.addAttribute("message", "fill new user's details");
        // DESCOMENTAR ADIANTE
        // List<String> currentUsers = userDao.getUsernameList();
        // model.addAttribute("message", "(we have now " + currentUsers.size() + " users) fill new user's details");
        // System.out.println("\n" + currentUsers.size() + " USERS: " + currentUsers.toString());
        return "newuser";
    }

    @GetMapping("/register")
    public String register(@RequestParam String username,
            @RequestParam String password,
            @RequestParam String email1,
            @RequestParam String email2,
            Model model) {

        String encodedPassword = new BCryptPasswordEncoder().encode(password);
        User u = new User(username, encodedPassword, email1, "ROLE_USER");

        userDao.saveUser(u);  // escrever na BD
        System.out.println("GRAVAR na BD: " + u.toString());
        model.addAttribute("user", u);   // deixar à disposição da view ?

        if (email1.equals(email2)) {
            model.addAttribute("message", "Emails do not match, please enter again!");
        } else {
            model.addAttribute("title", "registration page");
            model.addAttribute("message", "registration is OK");
        }

        return "register";
    }

    @GetMapping("/")
    public String defaultPage(Model model) {
        model.addAttribute("msg", "Welcome to Spring Security");
        return "index";
    }

    @GetMapping("/login")
    public String loginPage(Model model, @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {
        if (error != null) {
            model.addAttribute("error", "Invalid Credentials");
        }
        if (logout != null) {
            model.addAttribute("msg", "You have been successfully logged out");
        }
        return "login";
    }

    @GetMapping("/logout")
    public String logoutPage(Model model, HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/login?logout";
    }

    @GetMapping("/admin")
    public String adminPage(Model model) {
        model.addAttribute("title", "Administrator Control Panel");
        model.addAttribute("message", "This page demonstrates how to use Spring security");
        return "admin";
    }

}
