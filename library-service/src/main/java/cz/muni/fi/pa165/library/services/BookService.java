package cz.muni.fi.pa165.library.services;

import cz.muni.fi.pa165.library.entities.Book;
import cz.muni.fi.pa165.library.repositories.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Petr Janik 485122
 * @since 09.03.2020
 */
@Service
@Transactional
public class BookService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookService.class);

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public long createBook(Book book) {
        LOGGER.info("Creating book {}.", book);
        book = bookRepository.save(book);
        LOGGER.info("Created book with id {}.", book.getId());
        return book.getId();
    }

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

    public List<Book> findAll() {
        LOGGER.info("Finding all books.");
        return bookRepository.findAll();
    }

    public List<Book> findByTitle(String title) {
        LOGGER.info("Finding all books containing {} in title.", title);
        return bookRepository.findAll().stream().filter(b -> b.getTitle().contains(title)).collect(Collectors.toList());
    }

    public List<Book> findByAuthor(String author) {
        LOGGER.info("Finding all books containing {} as an author.", author);
        return bookRepository.findAll().stream().filter(b -> b.getAuthor().contains(author)).collect(Collectors.toList());
    }
}
