package com.example.demo.controllers;

import com.example.demo.model.Article;
import com.example.demo.repo.ArticleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class ArticleController {
    @Autowired
    private ArticleRepo articleRepo;
    @GetMapping("/")
    public String mainPage(){
        return "index";
    }
    @GetMapping("/bootstrap")
    public String addArticle() {
        return "addArticle";
    }
    @GetMapping("/students")
    public String students() {
        return "students";
    }
    @GetMapping("/indexStudents")
    public String indexStudents() {
        return "indexStudents";
    }
    @GetMapping("/index")
    public String index() {
        return "index";
    }
    @PostMapping("/addArticle")
    public String addArticle(@RequestParam String title, @RequestParam String content, @RequestParam String author){
        Article article = new Article(title, content, author);
        articleRepo.save(article);
        System.out.println(title);
        System.out.println(content);
        System.out.println(author);
        return "redirect:/";
    }


}
