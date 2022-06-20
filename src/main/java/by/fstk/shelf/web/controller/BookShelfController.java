package by.fstk.shelf.web.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BookShelfController {
    private final Logger logger = LogManager.getLogger(BookShelfController.class);

    @GetMapping("/books")
    public ModelAndView books() {
        logger.info("got book shelf");
        return new ModelAndView("book_shelf");
    }
}