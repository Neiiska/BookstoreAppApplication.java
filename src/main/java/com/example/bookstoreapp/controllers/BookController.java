package com.example.bookstoreapp.controllers;


import com.example.bookstoreapp.commands.BookCommand;
import com.example.bookstoreapp.converters.BookCommandToBook;
import com.example.bookstoreapp.model.Book;
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
public class BookController {
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private DetailRepository detailRepository;
    private PublishingHouseRepository publishingHouseRepository;
    private BookCommandToBook bookCommandToBook;

    public BookController(AuthorRepository authorRepository, BookRepository bookRepository, DetailRepository detailRepository, PublishingHouseRepository publishingHouseRepository, BookCommandToBook bookCommandToBook) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.detailRepository = detailRepository;
        this.publishingHouseRepository = publishingHouseRepository;
        this.bookCommandToBook = bookCommandToBook;
    }

    @GetMapping
    @RequestMapping(value = {"/books", "/book/list"})
    public String getBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "book/list";
    }

    @GetMapping
    @RequestMapping("/book/{id}/show")
    public String getBookDetails(Model model, @PathVariable("id") Long id) {
        model.addAttribute("book", bookRepository.findById(id).get());
        return "book/show";
    }

    @GetMapping
    @RequestMapping("/book/{id}/delete")
    public String deleteBook(@PathVariable("id") Long id) {
        bookRepository.deleteById(id);
        return "redirect:/books";
    }

    @GetMapping
    @RequestMapping("/book/new")
    public String newBook(Model model) {
        model.addAttribute("book", new BookCommand());
        model.addAttribute("authors", authorRepository.findAll());
        return "book/addedit";
    }

    @PostMapping("book")
    public String saveOrUpdate(@ModelAttribute BookCommand command){
        Book detachedBook = bookCommandToBook.convert(command);
        Book savedBook = bookRepository.save(detachedBook);

        return "redirect:/book/" + savedBook.getId() + "/show";
    }
}