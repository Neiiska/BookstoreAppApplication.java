package com.example.bookstoreapp.repositories;

import com.example.bookstoreapp.model.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface AuthorRepository extends CrudRepository<Author, Long> {
    Optional<Author> getFirstByFirstNameAndLastName(String firstName, String lastName);
}
