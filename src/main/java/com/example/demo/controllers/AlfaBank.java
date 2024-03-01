package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AlfaBank {
    @GetMapping("/alfa")
    public String alfa(Model model){
        model.addAttribute("alfa", "");
        return "alfaBank";
    }
}
