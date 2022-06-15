package com.example.bookstoreapp.controllers;


import com.example.bookstoreapp.commands.BookCommand;
import com.example.bookstoreapp.commands.DetailCommand;
import com.example.bookstoreapp.converters.DetailCommandToDetail;
import com.example.bookstoreapp.model.Book;
import com.example.bookstoreapp.model.Detail;
import com.example.bookstoreapp.model.PublishingHouse;
import com.example.bookstoreapp.repositories.AuthorRepository;
import com.example.bookstoreapp.repositories.BookRepository;
import com.example.bookstoreapp.repositories.DetailRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class DetailController {
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private DetailRepository detailRepository;
    private PublishingHouse publishingHouse;
    private DetailCommandToDetail detailCommandToDetail;

    public DetailController(AuthorRepository authorRepository, BookRepository bookRepository, DetailRepository detailRepository, DetailCommandToDetail detailCommandToDetail) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.detailRepository = detailRepository;
        this.detailCommandToDetail = detailCommandToDetail;
    }

    @GetMapping
    @RequestMapping(value = {"/details", "/detail/list"})
    public String getDetails(Model model) {
        model.addAttribute("details", detailRepository.findAll());
        return "detail/list";
    }

    @GetMapping
    @RequestMapping("/detail/{id}/show")
    public String getDetailDetails(Model model, @PathVariable("id") Long id) {
        model.addAttribute("detail", detailRepository.findById(id).get());
        return "detail/show";
    }

    @GetMapping
    @RequestMapping("/detail/{id}/delete")
    public String deleteDetail(@PathVariable("id") Long id) {
        detailRepository.deleteById(id);
        return "redirect:/details";
    }

    @GetMapping
    @RequestMapping("/detail/new")
    public String newDetail(Model model) {
        model.addAttribute("detail", new DetailCommand());
        model.addAttribute("books", bookRepository.findAll());
        return "detail/addedit";
    }

    @PostMapping("detail")
    public String saveOrUpdate(@ModelAttribute DetailCommand command){
        Detail detachedDetail = detailCommandToDetail.convert(command);
        Detail savedDetail = detailRepository.save(detachedDetail);

        return "redirect:/detail/" + savedDetail.getId() + "/show";
    }

}
