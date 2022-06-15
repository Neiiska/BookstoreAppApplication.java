package com.example.bookstoreapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PublishingHouseCommand {
    private Long id;
    private String name;
    private String email;
}
