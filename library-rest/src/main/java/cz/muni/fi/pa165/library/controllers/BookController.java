package cz.muni.fi.pa165.library.controllers;

import cz.muni.fi.pa165.library.entities.Book;
import cz.muni.fi.pa165.library.repositories.BookRepository;
import cz.muni.fi.pa165.library.services.BookService;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//TODO should operate with DTOs and Facade
@RestController
@Transactional
public class BookController extends AbstractController {

    private final BookService bookService;
    private final BookRepository bookRepository;

    public BookController(BookService bookService, BookRepository bookRepository) {
        this.bookService = bookService;
        this.bookRepository = bookRepository;
    }

    @PostMapping(value = "/books", consumes = MediaType.APPLICATION_JSON_VALUE)
    public long addBook(@RequestBody Book book) {
        return bookRepository.save(book).getId();
    }

    @DeleteMapping(value = "/books", params = "id")
    public void deleteBook(@RequestParam long id) {
        bookService.deleteBook(id);
    }

    @GetMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping(value = "/books", params = "title", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Book> findBooksByTitle(@RequestParam String title) {
        return bookService.findByTitle(title);
    }

    @GetMapping(value = "/books", params = "author", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Book> findBooksByAuthor(@RequestParam String author) {
        return bookService.findByAuthor(author);
    }
}
