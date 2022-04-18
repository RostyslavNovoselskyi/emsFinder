package com.dyploma.ems.controller;

import com.dyploma.ems.entity.Role;
import com.dyploma.ems.entity.User;
import com.dyploma.ems.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/add")
    public String registration(Model model){
        model.addAttribute("userForm",new User());
        return "register";
    }

    @PostMapping(path = "/add")
    public String addNewUser (
            @RequestParam String name,
            @RequestParam String password,
            @RequestParam String email,
            Model model){

        User userFromDb = userRepo.findByName(name);

        if (userFromDb != null){
            model.addAttribute("message", "User is already exists");
            return "register";
        }

        User user = new User(name, password, email);
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);
        return "redirect:/login";
    }
}
