package com.example.bookstoreapp.repositories;

import com.example.bookstoreapp.model.PublishingHouse;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface PublishingHouseRepository extends CrudRepository<PublishingHouse, Long> {

    Optional<PublishingHouse> getPublishingHouseByName(String name);
}
