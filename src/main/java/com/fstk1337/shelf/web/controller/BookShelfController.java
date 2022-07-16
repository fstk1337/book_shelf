package com.fstk1337.shelf.web.controller;

import com.fstk1337.shelf.app.service.BookService;
import com.fstk1337.shelf.web.dto.Book;
import com.fstk1337.shelf.web.dto.BookIdToRemove;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;


@Controller
@RequestMapping("/books")
public class BookShelfController {
    private final Logger logger = LogManager.getLogger(BookShelfController.class);
    private final BookService bookService;

    @Autowired
    public BookShelfController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String books(Model model) {
        logger.info(this.toString());
        model.addAttribute("book", new Book());
        model.addAttribute("bookList", bookService.getAllBooks());
        model.addAttribute("bookIdToRemove", new BookIdToRemove());
        return "book_shelf";
    }

    @PostMapping("/save")
    public String saveBook(Book book) {
        bookService.saveBook(book);
        logger.info("current repository size: " + bookService.getAllBooks().size());
        return "redirect:/books";
    }

    @PostMapping("/remove")
    public String removeBook(@Valid BookIdToRemove bookIdToRemove, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("book", new Book());
            model.addAttribute("bookList", bookService.getAllBooks());
            return "book_shelf";
        }
        bookService.removeBookById(bookIdToRemove.getId());
        logger.info("redirecting to book shelf");
        return "redirect:/books";
    }

    @PostMapping("removeByRegex")
    public String removeBooksByRegex(@RequestParam("bookAuthor") String author,
                                     @RequestParam("bookTitle") String title,
                                     @RequestParam("bookSize") String size) {
        int records = bookService.removeBooksByRegex(author, title, size);
        if (records > 0) {
            logger.info("books successfully removed: " + records);
        } else {
            logger.warn("There are no books found matching the inputs");
        }
        logger.info("redirecting to book shelf");
        return "redirect:/books";
    }
}