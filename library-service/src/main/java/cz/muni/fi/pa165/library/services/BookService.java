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

    public List<Book> findByTitle(String title) {
        return bookRepository.findAll().stream().filter(b -> b.getTitle().equals(title)).collect(Collectors.toList());
    }

    public List<Book> findByAuthor(String author) {
        return bookRepository.findAll().stream().filter(b -> b.getAuthor().equals(author)).collect(Collectors.toList());
    }

    public void deleteBook(long id) {
        Optional<Book> book = bookRepository.findById(id);
        book.ifPresent(b -> bookRepository.delete(b));
    }
}