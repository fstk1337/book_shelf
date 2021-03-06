package by.fstk.app.services;

import by.fstk.web.dto.LoginForm;
import by.fstk.web.dto.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {

    private final Logger logger = Logger.getLogger(LoginService.class);

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
