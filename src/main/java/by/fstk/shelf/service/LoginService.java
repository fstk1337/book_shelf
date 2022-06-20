package by.fstk.shelf.service;

import by.fstk.shelf.model.UserRepository;
import by.fstk.shelf.web.dto.LoginForm;
import by.fstk.shelf.web.dto.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {

    private final Logger logger = LogManager.getLogger(LoginService.class);

    public boolean authenticate(LoginForm loginForm) {
        logger.info("try auth with user-form: " + loginForm);
        return findUser(new User(loginForm.getUsername(), loginForm.getPassword()));
    }

    public boolean findUser(User user) {
        List<User> users = UserRepository.getUserRepo().retrieveAll();
        for (User item: users) {
            if (item.getName().equals(user.getName()) && item.getPassword().equals(user.getPassword()))
                return true;
        }
        return false;
    }
}
