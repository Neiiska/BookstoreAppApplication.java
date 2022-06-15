package com.example.bookstoreapp.tool;

import com.example.bookstoreapp.model.Author;
import com.example.bookstoreapp.model.Book;
import com.example.bookstoreapp.model.Detail;
import com.example.bookstoreapp.model.PublishingHouse;
import com.example.bookstoreapp.repositories.AuthorRepository;
import com.example.bookstoreapp.repositories.BookRepository;
import com.example.bookstoreapp.repositories.DetailRepository;
import com.example.bookstoreapp.repositories.PublishingHouseRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DBInflater implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private DetailRepository detailRepository;
    private PublishingHouseRepository publishingHouseRepository;
    public DBInflater(AuthorRepository authorRepository, BookRepository bookRepository, DetailRepository detailRepository, PublishingHouseRepository publishingHouseRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.detailRepository = detailRepository;
        this.publishingHouseRepository = publishingHouseRepository;
    }


    private void initData() {
        Author jennifer = new Author("Jennifer L.", "Armentrout","USA" );
        Book fromBloodAndAsh = new Book("From Blood and Ash", "The Blood and Ash", "Fantasy");
        Detail fbaa = new Detail("49,90pln", "Polish", "512");
        PublishingHouse youAndYA = new PublishingHouse("You&YA", "redakcja@youandya.pl");
        fromBloodAndAsh.getAuthors().add(jennifer);
        jennifer.getBooks().add(fromBloodAndAsh);
        fromBloodAndAsh.getDetails().add(fbaa);
        fbaa.getBooks().add(fromBloodAndAsh);
        youAndYA.getAuthors().add(jennifer);
        authorRepository.save(jennifer);
        bookRepository.save(fromBloodAndAsh);
        detailRepository.save(fbaa);
        publishingHouseRepository.save(youAndYA);

        Author sarah = new Author("Sarah J.", "Maas", "USA");
        Book crescentCity = new Book("Crescent City", "House of Earth and Blood", "Urban Fantasy");
        Detail cc = new Detail("39,99pln", "Polish", "560");
        PublishingHouse uroboros = new PublishingHouse("Uroboros", "uroboros@gmail.com");
        crescentCity.getAuthors().add(sarah);
        sarah.getBooks().add(crescentCity);
        crescentCity.getDetails().add(cc);
        cc.getBooks().add(crescentCity);
        uroboros.getAuthors().add(sarah);
        authorRepository.save(sarah);
        bookRepository.save(crescentCity);
        detailRepository.save(cc);
        publishingHouseRepository.save(uroboros);

        Author andrzej = new Author("Andrzej", "Sapkowski", "Poland");
        Book witcher1 = new Book("Witcher- Last Wish", "Witcher", "Fantasy");
        Detail wit = new Detail("38,50pln", "Polish", "330");
        PublishingHouse supernowa = new PublishingHouse("SUPERNOWA", "supernowa@gmail.com");
        witcher1.getAuthors().add(andrzej);
        andrzej.getBooks().add(witcher1);
        witcher1.getDetails().add(wit);
        wit.getBooks().add(witcher1);
        supernowa.getAuthors().add(andrzej);
        authorRepository.save(andrzej);
        bookRepository.save(witcher1);
        detailRepository.save(wit);
        publishingHouseRepository.save(supernowa);
    }


}
