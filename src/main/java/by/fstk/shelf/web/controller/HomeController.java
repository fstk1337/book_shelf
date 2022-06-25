package by.fstk.shelf.web.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/home")
public class HomeController {
    private final Logger logger = LogManager.getLogger(HomeController.class);

    @GetMapping
    public ModelAndView home() {
        logger.info("server returns index");
        return new ModelAndView("index");
    }
}
