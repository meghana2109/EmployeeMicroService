package com.springboot.microservice.Service;

import com.springboot.microservice.Model.Book;
import com.springboot.microservice.Repository.BookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public void saveBook(Book book){
        bookRepository.save(book);
    }

    public Optional<Book> getBookById(int id){
        return bookRepository.findById(id);
    }

    public Optional<Book> getBookByName(String name){
        return bookRepository.findByName(name);
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public void updateBookDetails(Book book){
        Optional<Book> oldBook = bookRepository.findById(book.getId());
        BeanUtils.copyProperties(book,oldBook);
    }

    public void deleteBookById(int id){
        bookRepository.deleteById(id);
    }

    public void deleteAllBooks(){
        bookRepository.deleteAll();
    }


}
