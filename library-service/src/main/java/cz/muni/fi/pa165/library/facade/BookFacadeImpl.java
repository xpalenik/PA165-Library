package cz.muni.fi.pa165.library.facade;

import cz.muni.fi.pa165.library.dto.BookDTO;
import cz.muni.fi.pa165.library.entities.Book;
import cz.muni.fi.pa165.library.services.BookService;
import cz.muni.fi.pa165.library.services.MappingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Petr Janik 485122
 * @since 06.04.2020
 */
@Service
@Transactional
public class BookFacadeImpl implements BookFacade {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookFacadeImpl.class);

    private final MappingService mappingService;
    private final BookService bookService;

    public BookFacadeImpl(MappingService mappingService, BookService bookService) {
        this.mappingService = mappingService;
        this.bookService = bookService;
    }

    @Override
    public long createBook(BookDTO book) {
        LOGGER.info("Creating book {}.", book);
        return bookService.createBook(mappingService.mapTo(book, Book.class));
    }

    @Override
    public long deleteBook(long id) {
        LOGGER.info("Deleting book with id {}.", id);
        return bookService.deleteBook(id);
    }

    @Override
    public List<BookDTO> findAllBooks() {
        LOGGER.info("Finding all books.");
        return mappingService.mapTo(bookService.findAll(), BookDTO.class);
    }

    @Override
    public List<BookDTO> findByTitle(String title) {
        LOGGER.info("Finding all books containing {} in title.", title);
        return mappingService.mapTo(bookService.findByTitle(title), BookDTO.class);
    }

    @Override
    public List<BookDTO> findByAuthor(String author) {
        LOGGER.info("Finding all books containing {} as an author.", author);
        return mappingService.mapTo(bookService.findByAuthor(author), BookDTO.class);
    }
}
