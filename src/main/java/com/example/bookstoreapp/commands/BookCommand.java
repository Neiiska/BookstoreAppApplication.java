package com.example.bookstoreapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookCommand {
    private Long id;
    private String title;
    private String series;
    private String genre;
    private String publishinghouse;
    private Long authorId;
}
