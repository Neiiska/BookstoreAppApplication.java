package com.example.bookstoreapp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(of = {"id"})
@Entity
public class PublishingHouse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;



    @ManyToMany
    private Set<Author> authors  = new HashSet<>();



    public PublishingHouse() {
    }
    public PublishingHouse(String name, String email) {
        this.name = name;
        this.email = email;
    }


}
