package com.fstk1337.shelf.app.util;

import com.fstk1337.shelf.web.dto.Book;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class RegexChecker {
    private static final Logger logger = LogManager.getLogger(RegexChecker.class);

    @Autowired
    public RegexChecker() {}

    private static boolean check(String regex, String input) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            logger.info("Pattern '" + regex + "' matches input '" + input + "'");
            return true;
        } else {
            logger.info("Pattern '" + regex + "' does not match input '" + input + "'");
            return false;
        }
    }

    public static List<Integer> checkBooks(List<Book> books, String authorRegex, String titleRegex, String sizeRegex) {
        List<Integer> ids = new ArrayList<>();
        for (Book book: books) {
            if (check(authorRegex, book.getAuthor()) &&
                check(titleRegex, book.getTitle()) &&
                check(sizeRegex, String.valueOf(book.getSize())))
            {
                logger.info(book + " matches patterns and is checked for removing");
                ids.add(book.getId());
            }
        }
        logger.info("Books checked for removing total: " + ids.size());
        return ids;
    }
}
