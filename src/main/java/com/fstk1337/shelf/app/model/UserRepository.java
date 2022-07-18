package com.fstk1337.shelf.app.model;

import com.fstk1337.shelf.web.dto.Book;
import com.fstk1337.shelf.web.dto.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository implements ProjectRepository<User> {

    private final Logger logger = LogManager.getLogger(BookRepository.class);
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> retrieveAll() {
        List<User> users = jdbcTemplate.query("SELECT * FROM `USER`",
                (ResultSet rs, int rowNum) -> {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("name"));
                    user.setPassword(rs.getString("password"));
                    return user;
                });
        return new ArrayList<>(users);
    }

    @Override
    public void store(User user) {
        if (user.getName().isEmpty() || user.getPassword().isEmpty()) {
            logger.warn("user info is empty, can't create");
        } else {
            MapSqlParameterSource parameterSource = new MapSqlParameterSource();
            parameterSource.addValue("name", user.getName());
            parameterSource.addValue("password", user.getPassword());
            logger.info("create new user: " + user);
            jdbcTemplate.update("INSERT INTO `USER` (name, `password`) " +
                    "VALUES (:name, :password)", parameterSource);
        }
    }

    @Override
    public boolean removeItemById(Integer userId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", userId);
        for(User user: retrieveAll()) {
            if (user.getId().equals(userId)) {
                logger.info("remove user completed: " + user);
                jdbcTemplate.update("DELETE FROM `USER` WHERE id = :id", parameterSource);
                return true;
            }
        }
        logger.warn("user id " + userId + " not found - remove failed");
        return false;
    }
}
