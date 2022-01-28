package com.springboot.practice.core.batch;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class StringReader implements ItemReader {
    private String[] books={"Head first Java","Thinking in Java","Java Black Book"};
    private int count=0;
    @Override
    public String read() throws UnexpectedInputException, ParseException, NonTransientResourceException {
        System.out.println("Inside Read Method");
        if(count< books.length){
            return books[count++];
        }else{
            count=0;
        }
        return null;
    }
}
