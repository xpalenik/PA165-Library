package cz.muni.fi.pa165.library.controllers;

import cz.muni.fi.pa165.library.dto.BookDTO;
import cz.muni.fi.pa165.library.facade.BookFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Transactional
public class BookController extends AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    private final BookFacade bookFacade;

    public BookController(BookFacade bookFacade) {
        this.bookFacade = bookFacade;
    }

    @PostMapping(value = "/books")
    public long createBook(@RequestBody BookDTO book) {
        LOGGER.info("Creating book {}.", book);
        return bookFacade.createBook(book);
    }

    @DeleteMapping(value = "/delete/book/{id}")
    public long deleteBook(@PathVariable long id) {
        LOGGER.info("Deleting book with id {}.", id);
        return bookFacade.deleteBook(id);
    }

    @GetMapping(value = "/books")
    public List<BookDTO> findAllBooks() {
        LOGGER.info("Finding all books.");
        return bookFacade.findAllBooks();
    }

    @GetMapping(value = "/books_title/{title}")
    public List<BookDTO> findByTitle(@PathVariable String title) {
        LOGGER.info("Finding all books containing {} in title.", title);
        return bookFacade.findByTitle(title);
    }

    @GetMapping(value = "/books_author/{author}")
    public List<BookDTO> findByAuthor(@PathVariable String author) {
        LOGGER.info("Finding all books containing {} as an author.", author);
        return bookFacade.findByAuthor(author);
    }

    @GetMapping(value = "/book_id/{id}")
    public BookDTO findById(@PathVariable long id) {
        return bookFacade.findById(id);
    }
}
