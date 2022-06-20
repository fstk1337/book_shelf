package by.fstk.shelf.service;


import by.fstk.shelf.model.UserRepository;
import by.fstk.shelf.web.dto.User;
import by.fstk.shelf.web.dto.UserForm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepo = UserRepository.getUserRepo();
    private final Logger logger = LogManager.getLogger(UserService.class);

    public boolean create(UserForm userForm) {
        logger.info("try to create user " + userForm);
        if (!userForm.getUsername().isEmpty() && !userForm.getPassword().isEmpty()) {
            userRepo.store(new User(userForm.getUsername(), userForm.getPassword()));
            return true;
        }
        return false;
    }
}
