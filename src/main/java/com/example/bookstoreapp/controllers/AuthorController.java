package com.example.bookstoreapp.controllers;


import com.example.bookstoreapp.commands.AuthorCommand;
import com.example.bookstoreapp.converters.AuthorCommandToAuthor;
import com.example.bookstoreapp.model.Author;
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
public class AuthorController {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private DetailRepository detailRepository;
    private PublishingHouse publishingHouse;
    private AuthorCommandToAuthor authorCommandToAuthor;
    public AuthorController(AuthorRepository authorRepository, BookRepository bookRepository, DetailRepository detailRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.detailRepository = detailRepository;
    }


    @RequestMapping(value = {"/authors", "/author/list"})
    public String getAuthor(Model model) {
        model.addAttribute("authors", authorRepository.findAll());
        return "author/list";
    }

    @RequestMapping("/author/{id}/books")
    public String getAuthorBooks(Model model, @PathVariable("id") Long id) {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isPresent()) {
            model.addAttribute("books", bookRepository.getAllByAuthorsIsContaining(author.get()));
            model.addAttribute("filter", "author: " + author.get().getFirstName() + " " + author.get().getLastName());
        } else {
            model.addAttribute("books", new ArrayList<>());
            model.addAttribute("filter", "author for this id doesn't exist");
        }
        return "author/list";
    }


    @RequestMapping("/author/{id}/show")
    public String getAuthorDetails(Model model, @PathVariable("id") Long id) {
        model.addAttribute("author", authorRepository.findById(id).get());
        return "author/show";
    }

    @RequestMapping("/author{id}/delete")
    public String deleteAuthor(@PathVariable("id") Long id) {
        authorRepository.deleteById(id);
        return "redirect:/authors";
    }

    @GetMapping
    @RequestMapping("/author/new")
    public String newBook(Model model){
        model.addAttribute("author", new AuthorCommand());
        return "author/addedit";
    }

    @PostMapping("author")
    public String saveOrUpdate(@ModelAttribute AuthorCommand command){

        Optional<Author> authorOptional = authorRepository.getFirstByFirstNameAndLastName(command.getFirstName(), command.getLastName());

        if (!authorOptional.isPresent()) {
            Author detachedAuthor = authorCommandToAuthor.convert(command);
            Author savedAuthor = authorRepository.save(detachedAuthor);
            return "redirect:/author/" + savedAuthor.getId() + "/show";
        } else {
            //TODO: error message to template
            System.out.println("Sorry, there's such author in db");
            return "redirect:/author/" + authorOptional.get().getId() + "/show";
        }
    }
}
