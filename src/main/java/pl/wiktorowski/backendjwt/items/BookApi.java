package pl.wiktorowski.backendjwt.items;

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
        bookList.add(new Book("In Search of Lost Time","Marcel Proust"));
        bookList.add(new Book("Don Quixote","Miguel de Cervantes"));
        bookList.add(new Book("Ulysses","James Joyce"));
        bookList.add(new Book("One Hundred Years of Solitude","One Hundred Years of Solitude"));
        bookList.add(new Book("The Great Gatsby","F. Scott Fitzgerald"));
        bookList.add(new Book("Moby Dick","Herman Melville"));
        bookList.add(new Book("Fear of Flying","Erica Jong"));
        bookList.add(new Book("The Sound and the Fury","William Faulkner"));


        return bookList;

    }


}
