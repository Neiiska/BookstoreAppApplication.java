package com.example.bookstoreapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DetailCommand {
    private Long id;
    private String price;
    private String language;
    private String pages;
    private Long bookId;
}
