package com.fstk1337.shelf.app.service;

import com.fstk1337.shelf.app.model.BookRepository;
import com.fstk1337.shelf.web.dto.Book;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepo;
    private final Logger logger = LogManager.getLogger(BookService.class);

    @Autowired
    public BookService(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<Book> getAllBooks() {
        return bookRepo.retrieveAll();
    }

    public void saveBook(Book book) {
        bookRepo.store(book);
    }

    public boolean removeBookById(String bookIdToRemove) {
        return bookRepo.removeItemById(bookIdToRemove);
    }

    public int removeBooksByRegex(String author, String title, String size) {
        return bookRepo.removeBooksByRegex(author, title, size);
    }
}
