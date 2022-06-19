package by.fstk.app.services;

import by.fstk.web.dto.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository implements ProjectRepository<User> {

    private static UserRepository userRepo;
    private final Logger logger = LogManager.getLogger(UserRepository.class);
    private final List<User> users = new ArrayList<>();

    private UserRepository() {
    }

    public static UserRepository getUserRepo() {
        if (userRepo == null)
            userRepo = new UserRepository();
        return userRepo;
    }

    @Override
    public List<User> retrieveAll() {
        return new ArrayList<>(users);
    }

    @Override
    public void store(User user) {
        if (user.getName().isEmpty() || user.getPassword().isEmpty()) {
            logger.info("user info is empty, can't create");
        } else {
            user.setId(user.hashCode());
            logger.info("created new user: " + user);
            users.add(user);
        }
    }

    @Override
    public boolean removeItemById(Integer userIdToRemove) {
        return false;
    }

}
