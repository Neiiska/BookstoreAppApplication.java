package com.example.bookstoreapp.controllers;

import com.example.bookstoreapp.commands.PublishingHouseCommand;
import com.example.bookstoreapp.converters.PublishingHouseCommandToPublishingHouse;
import com.example.bookstoreapp.model.PublishingHouse;
import com.example.bookstoreapp.repositories.AuthorRepository;
import com.example.bookstoreapp.repositories.BookRepository;
import com.example.bookstoreapp.repositories.DetailRepository;
import com.example.bookstoreapp.repositories.PublishingHouseRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class PublishingHouseController {
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private DetailRepository detailRepository;
    private PublishingHouseRepository publishingHouseRepository;
    private PublishingHouseCommandToPublishingHouse publishingHouseCommandToPublishingHouse;

    public PublishingHouseController(AuthorRepository authorRepository, BookRepository bookRepository, DetailRepository detailRepository, PublishingHouseRepository publishingHouseRepository, PublishingHouseCommandToPublishingHouse publishingHouseCommandToPublishingHouse) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.detailRepository = detailRepository;
        this.publishingHouseRepository = publishingHouseRepository;
        this.publishingHouseCommandToPublishingHouse = publishingHouseCommandToPublishingHouse;
    }

    @RequestMapping(value = {"/publishinghouses", "/publishinghouse/list"})
    public String getPublishingHouse(Model model) {
        model.addAttribute("publishinghouses", publishingHouseRepository.findAll());
        return "publishinghouse/list";
    }

    @RequestMapping("/publishinghouse/{id}/show")
    public String getPublishingHouseDetails(Model model, @PathVariable("id") Long id) {
        model.addAttribute("publishinghouse", publishingHouseRepository.findById(id).get());
        return "publishinghouse/show";
    }

    @RequestMapping("/publishinghouse/{id}/delete")
    public String deletePublishingHouse(@PathVariable("id") Long id) {
        publishingHouseRepository.deleteById(id);
        return "redirect:/publishinghouses";
    }

    @GetMapping
    @RequestMapping("/publishinghouse/new")
    public String newBook(Model model){
        model.addAttribute("publishinghouse", new PublishingHouseCommand());
        return "publishinghouse/addedit";
    }

    @PostMapping("publishinghouse")
    public String saveOrUpdate(@ModelAttribute PublishingHouseCommand command){

        Optional<PublishingHouse> publishingHouseOptional = publishingHouseRepository.getPublishingHouseByName(command.getName());

        if (!publishingHouseOptional.isPresent()) {
            PublishingHouse detachedPublishingHouse = publishingHouseCommandToPublishingHouse.convert(command);
            PublishingHouse savedPublishingHouse = publishingHouseRepository.save(detachedPublishingHouse);
            return "redirect:/publishinghouse/" + savedPublishingHouse.getId() + "/show";
        } else {
            //TODO: error message to template
            System.out.println("Sorry, there's such publishing house in db");
            return "redirect:/publishinghouse" + publishingHouseOptional.get().getId() + "/show";
        }
    }






}
