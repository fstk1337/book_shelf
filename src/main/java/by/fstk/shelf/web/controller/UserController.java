package by.fstk.shelf.web.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
    private final Logger logger = LogManager.getLogger(UserController.class);

    @GetMapping("/create")
    public ModelAndView create() {
        logger.info("server returns create_page");
        return new ModelAndView("create_page");
    }
}
