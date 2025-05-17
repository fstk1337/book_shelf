package com.fstk1337.shelf.app.service;

import com.fstk1337.shelf.app.model.UserRepository;
import com.fstk1337.shelf.web.dto.LoginForm;
import com.fstk1337.shelf.web.dto.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {
    private final Logger logger = LogManager.getLogger(LoginService.class);
    private final UserRepository userRepo;

    @Autowired
    public LoginService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public boolean authenticate(LoginForm loginForm) {
        logger.info("try auth with user-form: " + loginForm);
        List<User> users = userRepo.retrieveAll();
        String password = "";
        for (User user: users) {
            if (user.getName().equals(loginForm.getUsername())) {
                password = user.getPassword();
                break;
            }
        }
        if (password.isEmpty()) return false;
        return loginForm.getPassword().equals(password);
    }
}
