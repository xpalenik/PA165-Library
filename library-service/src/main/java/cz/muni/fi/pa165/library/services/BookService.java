package cz.muni.fi.pa165.library.services;

import cz.muni.fi.pa165.library.entities.Book;
import cz.muni.fi.pa165.library.repositories.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Petr Janik 485122
 * @since 09.03.2020
 *
 * business logic class
 */
@Service
public class BookService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookService.class);

    @Autowired
    private BookRepository bookRepository;

    /**
     * create a book
     *
     * @param book
     * @return is of created book
     */
    public long createBook(Book book) {
        LOGGER.info("Creating book {}.", book);
        book = bookRepository.save(book);
        LOGGER.info("Created book with id {}.", book.getId());
        return book.getId();
    }

    /**
     * deletes book by id
     *
     * @param id
     * @return id of deleted book
     */
    public long deleteBook(long id) {
        LOGGER.info("Deleting book with id {}.", id);
        Optional<Book> book = bookRepository.findById(id);
        book.ifPresent(b -> {
                    LOGGER.info("Book with id {} has been found.", id);
                    bookRepository.delete(b);
                }
        );
        return id;
    }

    /**
     *
     * @return list of all books
     */
    public List<Book> findAll() {
        LOGGER.info("Finding all books.");
        return bookRepository.findAll();
    }

    /**
     * method gets all books having certain title
     *
     * @param title
     * @return list of books having certain title
     */
    public List<Book> findByTitle(String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title is null");
        }
        LOGGER.info("Finding all books containing {} in title.", title);
        return bookRepository.findAll().stream().filter(b -> b.getTitle().contains(title)).collect(Collectors.toList());
    }

    /**
     * method gets all books having certain author
     *
     * @param author
     * @return list of books having certain author
     */
    public List<Book> findByAuthor(String author) {
        if (author == null || author.isEmpty()) {
            throw new IllegalArgumentException("Author is null");
        }
        LOGGER.info("Finding all books containing {} as an author.", author);
        return bookRepository.findAll().stream().filter(b -> b.getAuthor().contains(author)).collect(Collectors.toList());
    }

    /**
     *
     * @param id
     * @return book having certain id
     */
    public Book findById(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("ID can not be less than 0.");
        }
        return bookRepository.findById(id).get();
    }
}
