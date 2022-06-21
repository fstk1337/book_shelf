package by.fstk.shelf.web.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    private final Logger logger = LogManager.getLogger(LoginController.class);

    @GetMapping("/login")
    public ModelAndView login() {
        logger.info("server returns login_page");
        return new ModelAndView("login_page");
    }
}
