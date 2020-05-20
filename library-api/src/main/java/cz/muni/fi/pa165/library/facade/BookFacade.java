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

    List<BookDTO> findAllBooks();

    List<BookDTO> findByTitle(String title);

    List<BookDTO> findByAuthor(String author);

    BookDTO findById(long id);
}
