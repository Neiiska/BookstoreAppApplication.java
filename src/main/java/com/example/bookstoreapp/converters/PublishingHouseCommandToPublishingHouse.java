package com.example.bookstoreapp.converters;

import com.example.bookstoreapp.commands.PublishingHouseCommand;
import com.example.bookstoreapp.model.PublishingHouse;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class PublishingHouseCommandToPublishingHouse implements Converter<PublishingHouseCommand, PublishingHouse>{

    @Synchronized
    @Nullable
    @Override
    public PublishingHouse convert(PublishingHouseCommand source){
        if(source == null){
            return null;
        }

        final PublishingHouse publishingHouse = new PublishingHouse();
        publishingHouse.setName(source.getName());
        publishingHouse.setEmail(source.getEmail());

        return publishingHouse;
    }

}
