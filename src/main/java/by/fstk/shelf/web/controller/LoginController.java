package by.fstk.shelf.web.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    private final Logger logger = LogManager.getLogger(LoginController.class);

    @GetMapping(value = "/login")
    public ModelAndView login() {
        logger.info("GET /login returns login_page.html");
        return new ModelAndView("login_page");
    }
}
