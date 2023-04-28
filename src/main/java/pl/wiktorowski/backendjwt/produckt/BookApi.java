package pl.wiktorowski.backendjwt.produckt;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController

public class BookApi {

    @GetMapping("/book")
    public List<Book> get(){

        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("Bogaty ojciec Biedny Ojciec","Kyosaki"));
        bookList.add(new Book("Czysty kod","Martin Robert"));


        return bookList;

    }


}
