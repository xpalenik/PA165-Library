package cz.muni.fi.pa165.library.facade;

import cz.muni.fi.pa165.library.dto.BookDTO;

import java.util.List;

/**
 * @author Petr Janik 485122
 * @since 06.04.2020
 */
public interface BookFacade {
    long createBook(BookDTO book);

    long deleteBook(long id);

    List<BookDTO> getAllBooks();

    List<BookDTO> getBooksByTitle(String title);

    List<BookDTO> getBooksByAuthor(String author);

    BookDTO getBookById(long id);
}
