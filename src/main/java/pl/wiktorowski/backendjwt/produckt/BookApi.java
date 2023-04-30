package pl.wiktorowski.backendjwt.produckt;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
//@CrossOrigin(origins = "localhost:4200")

public class BookApi {



    @GetMapping("/api/books")
    public List<Book> get(){

        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("Bogaty ojciec Biedny Ojciec","Kyosaki"));
        bookList.add(new Book("Czysty kod","Martin Robert"));


        return bookList;

    }


}
