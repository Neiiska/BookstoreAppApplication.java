package com.example.bookstoreapp.repositories;

import com.example.bookstoreapp.model.Author;
import com.example.bookstoreapp.model.Book;
import org.springframework.data.repository.CrudRepository;


public interface BookRepository extends CrudRepository<Book, Long> {
    Object getAllByAuthorsIsContaining(Author author);
}
