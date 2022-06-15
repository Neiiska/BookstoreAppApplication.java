package com.example.bookstoreapp.converters;

import com.example.bookstoreapp.commands.BookCommand;
import com.example.bookstoreapp.model.Author;
import com.example.bookstoreapp.model.Book;
import com.example.bookstoreapp.repositories.AuthorRepository;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BookCommandToBook implements Converter<BookCommand, Book>{


    @Synchronized
    @Nullable
    @Override
    public Book convert(BookCommand source){
        if (source == null){
            return null;
        }

        final Book book = new Book();
        book.setTitle(source.getTitle());
        book.setSeries(source.getSeries());
        book.setGenre(source.getGenre());


        return book;
    }
}
