package ru.gb.springdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // получить книгу по id
    public Book getBookById(Long id) {
        return bookRepository.getBookById(id);
    }

    //получить список всех книг
    public List<Book> getAllBooks() { return bookRepository.getAllBooks(); }

    // создание книги
    public Book addBook(Book book){
        return bookRepository.addBook(book);
    }

    //обновление книг
    public Book updateBooks(Long id, Book book){
        return bookRepository.updateBooks(id, book);
    }

    // удаление книги
    public void deleteBook(Long id){
        bookRepository.deleteBook(id);
    }

}
