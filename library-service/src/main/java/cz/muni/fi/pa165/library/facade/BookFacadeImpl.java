package cz.muni.fi.pa165.library.facade;

import cz.muni.fi.pa165.library.dto.BookDTO;
import cz.muni.fi.pa165.library.entities.Book;
import cz.muni.fi.pa165.library.services.BookService;
import cz.muni.fi.pa165.library.services.MappingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Petr Janik 485122 && Katarina Hermanova 433511
 * @since 06.04.2020
 */
@Service
@Transactional
public class BookFacadeImpl implements BookFacade {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookFacadeImpl.class);

    @Autowired
    private MappingService mappingService;

    @Autowired
    private BookService bookService;

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
        List<BookDTO> resultDto = new ArrayList<>();

        for (Book result : bookService.findAll()) {
            resultDto.add(
                    mappingService.mapTo(result, BookDTO.class)
            );
        }

        return resultDto;
    }

    @Override
    public List<BookDTO> findByTitle(String title) {
        LOGGER.info("Finding all books containing {} in title.", title);
        List<BookDTO> resultDto = new ArrayList<>();

        for (Book result : bookService.findByTitle(title)) {
            resultDto.add(
                    mappingService.mapTo(result, BookDTO.class)
            );
        }

        return resultDto;
    }

    @Override
    public List<BookDTO> findByAuthor(String author) {
        LOGGER.info("Finding all books containing {} as an author.", author);
        List<BookDTO> resultDto = new ArrayList<>();

        for (Book result : bookService.findByAuthor(author)) {
            resultDto.add(
                    mappingService.mapTo(result, BookDTO.class)
            );
        }

        return resultDto;
    }

    @Override
    public BookDTO findById(long id) {
        Book user = bookService.findById(id);
        if (user != null) {
            return mappingService.mapTo(user, BookDTO.class);
        }
        return null;
    }
}
