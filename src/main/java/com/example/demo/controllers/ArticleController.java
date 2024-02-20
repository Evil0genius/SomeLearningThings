package com.example.demo.controllers;

import com.example.demo.model.Article;
import com.example.demo.repo.ArticleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;


@Controller
public class ArticleController {
    @Autowired
    private ArticleRepo articleRepo;
    @GetMapping("/")
    public String mainPage(Model model){
        Iterable<Article> articles = articleRepo.findAll();
        model.addAttribute("articles", articles);
        return "index";
    }
    @GetMapping("/bootstrap")
    public String addArticle() {
        return "addArticle";
    }
    @GetMapping("/blog/{id}")
    public String showArticle(@PathVariable(value = "id") long id, Model model){
        Optional<Article> article = articleRepo.findById(id);
        model.addAttribute("article", article.get());
        return "article";
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
    @GetMapping("/editArticle/{id}")
    public String editArticle(@PathVariable(value = "id") long id, Model model) {
        Optional<Article> article = articleRepo.findById(id);
        model.addAttribute("article", article.get());
        return "editArticle";
    }
    @PostMapping("/editArticle")
    public String editArticle(@RequestParam String title, @RequestParam String content, @RequestParam String author,
    @RequestParam Long id){
        Article article = new Article(title, content, author );
        article.id = id;
        articleRepo.save(article);
        return "redirect:/blog/"+id;
    }
    @GetMapping("/deleteArticle/{id}")
    public String deleteArticle(@PathVariable(value = "id") long id, Model model) {
        Optional<Article> article = articleRepo.findById(id);
        model.addAttribute("article", article.get());
        return "deleteArticle";
    }
    @PostMapping("/deleteArticle")
    public String deleteArticle(@RequestParam Long id) {
        articleRepo.deleteById(id);
        return "redirect:/";
    }


}
