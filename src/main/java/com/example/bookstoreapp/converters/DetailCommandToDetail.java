package com.example.bookstoreapp.converters;

import com.example.bookstoreapp.commands.DetailCommand;
import com.example.bookstoreapp.model.Detail;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class DetailCommandToDetail implements Converter<DetailCommand, Detail>{

    @Synchronized
    @Nullable
    @Override
    public Detail convert(DetailCommand source){
        if(source == null){
            return null;
        }

        final Detail detail = new Detail();
        detail.setPrice(source.getPrice());
        detail.setLanguage(source.getLanguage());
        detail.setPages(source.getPages());

        return detail;
    }





}
