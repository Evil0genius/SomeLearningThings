package com.example.demo.controllers;

import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    UserRepo userRepo;
    @GetMapping("/reg")
    public String reg(@PathVariable(value = "error")String error, Model model){
        boolean err = false;
        if(error != null) err = true;
        model.addAttribute("error", err);
        return "reg";


    }
    @PostMapping("/regUser")
    public String regUser(@RequestParam String name,
                      @RequestParam String surname,
                      @RequestParam String email,
                      @RequestParam String pass
                      ){
        Optional<User> oUser = userRepo.findByEmail(email);
        if(oUser.isEmpty()){
            User user = new User(name, surname, email, pass );
            userRepo.save(user);
            System.out.println("Пльзоватнъель "+name+"существует");
            return "redirect:/login";
        }else System.out.println("Не найден");

        return "redirect:/reg?error=exist";
    }
    @GetMapping("/login")
    public String login(@PathVariable(value = "error")String error, Model model){
        boolean err = false;
        if(error != null){
            err= true;
        }
        model.addAttribute("error", err);
        return "login";
    }
    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String pass){
        Optional<User> oUser = userRepo.findByEmailAndPass(email, pass);
        if(oUser.isEmpty()){
            return "redirect:/login?error=notFound";

        }else {
            return "redirect:/";
        }

    }

}
