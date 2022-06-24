package by.fstk.shelf.app.model;

import by.fstk.shelf.app.service.IdProvider;
import by.fstk.shelf.app.util.RegexChecker;
import by.fstk.shelf.web.dto.Book;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository implements ProjectRepository<Book>, ApplicationContextAware {

    private final Logger logger = LogManager.getLogger(BookRepository.class);
    private final List<Book> repo = new ArrayList<>();
    private ApplicationContext context;

    @Override
    public List<Book> retrieveAll() {
        return new ArrayList<>(repo);
    }

    @Override
    public void store(Book book) {
        if (book.getAuthor().isEmpty() && book.getTitle().isEmpty() && book.getSize() == null) {
            logger.warn("book info is empty, can't save");
        } else {
            book.setId(context.getBean(IdProvider.class).provideId(book));
            logger.info("store new book: " + book);
            repo.add(book);
        }
    }

    @Override
    public boolean removeItemById(String bookIdToRemove) {
        for(Book book: retrieveAll()) {
            if (book.getId().equals(bookIdToRemove)) {
                logger.info("remove book completed: " + book);
                return repo.remove(book);
            }
        }
        logger.warn("book id " + bookIdToRemove + " not found - remove failed");
        return false;
    }

    public int removeBooksByRegex(String author, String title, String size) {
        List<String> matchIDs = RegexChecker.checkBooks(retrieveAll(), author, title, size);
        int removes = 0;
        for (String id: matchIDs) {
            if (removeItemById(id)) removes++;
        }
        return removes;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public void defaultInit() {
        logger.info("default INIT in book repo bean");
    }

    public void defaultDestroy() {
        logger.info("default DESTROY in book repo bean");
    }
}
