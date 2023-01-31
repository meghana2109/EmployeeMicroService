package com.springboot.microservice.Controller;

import com.springboot.microservice.Model.Book;
import com.springboot.microservice.Repository.BookRepository;
import com.springboot.microservice.Service.BookService;
import net.minidev.json.JSONObject;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {
//    Book[] bookArray;
//    Book b1 = new Book();
    List<Book> books = new ArrayList<Book>();
    //{ b1, b2, b3}

    @Autowired
    BookService bookService;

    @GetMapping
    public String sampleMethod() {
        return "Hi this is book page";
    }

    @GetMapping(value = "/search")
    public Optional<Book> getBookById(@RequestParam int id){
        Optional<Book> b = bookService.getBookById(id);
        if(b.isPresent()){
            return b;
        }

        return Optional.empty();
    }

    @GetMapping(value = "/search/{name}")
    public Book getBookByName(@PathVariable @NotNull String name) {
        for(Book book : books) {
            if (name.equalsIgnoreCase(book.getName())) {
                return book;
            }
        }
        return null;
    }
    @GetMapping(value = "/search/all")
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @PostMapping(value = "/add")
    public String addNewBook(@RequestBody Book b1) {
        bookService.saveBook(b1);
        return "Book added successfully";
    }

    @PutMapping(value = "/update")
    public String updateBookDetails(@RequestBody Book b1){
        Optional<Book> book = bookService.getBookById(b1.getId());
        if(book.isPresent()){
            BeanUtils.copyProperties(b1,book);
            bookService.saveBook(b1);
            return "Book details updated successfully";
        }

        return null;
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteBookById(@PathVariable int id) {
        if(bookService.getBookById(id).isPresent()){
            bookService.deleteBookById(id);
            return "Book"+id +" deleted Successfully";
        }
        return null;
    }

    @DeleteMapping(value = "/deleteAll")
    public String deleteAllBooks(){
        bookService.deleteAllBooks();
        return " All Books Deleted Successfully";
    }

    @GetMapping("/listHeaders")
    public ResponseEntity<String> listAllHeaders(
            @RequestHeader Map<String, String> headers) {
        JSONObject json = new JSONObject();
        headers.forEach((key, value) -> {
            //console.info(String.format("Header '%s' = %s", key, value));
            json.appendField(key, value);
            System.out.println(json);
        });

        return new ResponseEntity<String>(
                String.format("Listed %d headers", headers.size()), HttpStatus.OK);
    }

    @GetMapping(value = "/exist")
    public String checkIfBookExist(@RequestHeader int id) {
        if(bookService.getBookById(id).isPresent()){
            return "Book "+id+" found successfully";
        }
        return "Book not found";
    }

}
