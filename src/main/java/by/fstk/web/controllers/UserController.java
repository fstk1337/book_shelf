package by.fstk.web.controllers;

import by.fstk.app.services.UserService;
import by.fstk.web.dto.UserForm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/create")
public class UserController {
    private final Logger logger = LogManager.getLogger(UserController.class);
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String create(Model model) {
        logger.info("GET /create returns create_page.html");
        model.addAttribute("userForm", new UserForm());
        return "create_page";
    }

    @PostMapping("/new")
    public String create(UserForm userForm) {
        if (userService.create(userForm)) {
            logger.info("creating new user OK redirect to login");
            return "redirect:/login";
        } else {
            logger.info("create FAIL redirect back to create");
            return "redirect:/create";
        }
    }
}
