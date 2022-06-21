package by.fstk.shelf.model;

import by.fstk.shelf.util.RegexChecker;
import by.fstk.shelf.web.dto.Book;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository implements ProjectRepository<Book> {

    private final Logger logger = LogManager.getLogger(BookRepository.class);
    private final List<Book> repo = new ArrayList<>();

    @Override
    public List<Book> retrieveAll() {
        return new ArrayList<>(repo);
    }

    @Override
    public void store(Book book) {
        if (book.getAuthor().isEmpty() && book.getTitle().isEmpty() && book.getSize() == null) {
            logger.warn("book info is empty, can't save");
        } else {
            book.setId(book.hashCode());
            logger.info("store new book: " + book);
            repo.add(book);
        }
    }

    @Override
    public boolean removeItemById(Integer bookIdToRemove) {
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
        List<Integer> matchIDs = RegexChecker.checkBooks(retrieveAll(), author, title, size);
        int removes = 0;
        for (int id: matchIDs) {
            if (removeItemById(id)) removes++;
        }
        return removes;
    }
}
