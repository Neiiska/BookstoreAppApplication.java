package com.example.bookstoreapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class BookstoreController {

    @RequestMapping(value = {"/"})
    public String getAuthors() {
        return "MainPage";
    }

}
