package com.example.bookstoreapp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(of = {"id"})
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String country;


    @ManyToOne
    private PublishingHouse publishingHouse;
    @ManyToMany(mappedBy = "authors", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Book> books  = new HashSet<>();


    public Author() {
    }
    public Author(String firstName, String lastName, String country) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
    }

    public Long getId() {
        return id;
    }
    public String getLastName() {
        return lastName;
    }
}
