package cz.muni.fi.pa165.library.controllers;

import cz.muni.fi.pa165.library.dto.BookDTO;
import cz.muni.fi.pa165.library.facade.BookFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
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

    @PostMapping(value = "/books", consumes = MediaType.APPLICATION_JSON_VALUE)
    public long createBook(@RequestBody BookDTO book) {
        LOGGER.info("Creating book {}.", book);
        return bookFacade.createBook(book);
    }

    @DeleteMapping(value = "/books", params = "id")
    public long deleteBook(@RequestParam long id) {
        LOGGER.info("Deleting book with id {}.", id);
        return bookFacade.deleteBook(id);
    }

    @GetMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BookDTO> findAllBooks() {
        LOGGER.info("Finding all books.");
        return bookFacade.findAllBooks();
    }

    @GetMapping(value = "/books", params = "title", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BookDTO> findByTitle(@RequestParam String title) {
        LOGGER.info("Finding all books containing {} in title.", title);
        return bookFacade.findByTitle(title);
    }

    @GetMapping(value = "/books", params = "author", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BookDTO> findByAuthor(@RequestParam String author) {
        LOGGER.info("Finding all books containing {} as an author.", author);
        return bookFacade.findByAuthor(author);
    }
}
