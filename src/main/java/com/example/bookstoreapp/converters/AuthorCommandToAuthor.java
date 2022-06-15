package com.example.bookstoreapp.converters;

import com.example.bookstoreapp.commands.AuthorCommand;
import com.example.bookstoreapp.model.Author;
import com.example.bookstoreapp.repositories.AuthorRepository;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthorCommandToAuthor implements Converter<AuthorCommand, Author> {


    private final AuthorRepository authorRepository;

    public AuthorCommandToAuthor(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }


    @Synchronized
    @Nullable
    @Override
    public Author convert(AuthorCommand source){
        if(source == null){
            return null;
        }

        final Author author = new Author();
        author.setFirstName(source.getFirstName());
        author.setLastName(source.getLastName());
        author.setCountry(source.getCountry());


        return author;
    }

}
