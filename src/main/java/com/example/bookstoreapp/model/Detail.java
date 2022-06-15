package com.example.bookstoreapp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(of = {"id"})
@Entity
public class Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String price;
    private String language;
    private String pages;


    @ManyToMany
    private Set<Book> books  = new HashSet<>();


    public Detail() {
    }
    public Detail(String price, String language, String pages) {
        this.price = price;
        this.language = language;
        this.pages = pages;
    }


}
