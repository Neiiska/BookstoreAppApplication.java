package com.example.bookstoreapp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Data
@EqualsAndHashCode(of = {"id"})
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String series;
    private String genre;



    @ManyToOne
    private PublishingHouse publishingHouse;
    @ManyToMany(mappedBy = "books", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Detail> details  = new HashSet<>();
    @ManyToMany
    private Set<Author> authors  = new HashSet<>();



    public Book() {
    }
    public Book(String title, String series, String genre) {
        this.title = title;
        this.series = series;
        this.genre = genre;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
