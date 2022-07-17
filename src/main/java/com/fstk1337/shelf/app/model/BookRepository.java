package com.fstk1337.shelf.app.model;

import com.fstk1337.shelf.app.service.IdProvider;
import com.fstk1337.shelf.app.util.RegexChecker;
import com.fstk1337.shelf.web.dto.Book;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository implements ProjectRepository<Book>, ApplicationContextAware {

    private final Logger logger = LogManager.getLogger(BookRepository.class);
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private ApplicationContext context;

    @Autowired
    public BookRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Book> retrieveAll() {
        List<Book> books = jdbcTemplate.query("SELECT * FROM book",
                (ResultSet rs, int rowNum) -> {
                    Book book = new Book();
                    book.setId(rs.getInt("id"));
                    book.setAuthor(rs.getString("author"));
                    book.setTitle(rs.getString("title"));
                    book.setSize(rs.getInt("size"));
                    return book;
                });
        return new ArrayList<>(books);
    }

    @Override
    public void store(Book book) {
        if (book.getAuthor().isEmpty() && book.getTitle().isEmpty() && book.getSize() == null) {
            logger.warn("book info is empty, can't save");
        } else {
            book.setId(context.getBean(IdProvider.class).provideId(book));
            MapSqlParameterSource parameterSource = new MapSqlParameterSource();
            parameterSource.addValue("author", book.getAuthor());
            parameterSource.addValue("title", book.getTitle());
            parameterSource.addValue("size", book.getSize());
            logger.info("store new book: " + book);
            jdbcTemplate.update("INSERT INTO book (author, title, size) " +
                    "VALUES (:author, :title, :size)", parameterSource);
        }
    }

    @Override
    public boolean removeItemById(Integer bookId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", bookId);
        for(Book book: retrieveAll()) {
            if (book.getId().equals(bookId)) {
                logger.info("remove book completed: " + book);
                jdbcTemplate.update("DELETE FROM book WHERE id = :id", parameterSource);
                return true;
            }
        }
        logger.warn("book id " + bookId + " not found - remove failed");
        return false;
    }

    public int removeBooksByRegex(String author, String title, String size) {
        List<Integer> matchIDs = RegexChecker.checkBooks(retrieveAll(), author, title, size);
        int removes = 0;
        for (Integer id: matchIDs) {
            if (removeItemById(id)) removes++;
        }
        return removes;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
