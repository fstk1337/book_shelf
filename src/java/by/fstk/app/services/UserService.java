package by.fstk.app.services;


import by.fstk.web.dto.User;
import by.fstk.web.dto.UserForm;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepo = UserRepository.getUserRepo();
    private final Logger logger = Logger.getLogger(UserService.class);

    public boolean create(UserForm userForm) {
        logger.info("try to create user " + userForm);
        if (!userForm.getUsername().isEmpty() && !userForm.getPassword().isEmpty()) {
            userRepo.store(new User(userForm.getUsername(), userForm.getPassword()));
            return true;
        }
        return false;
    }
}
