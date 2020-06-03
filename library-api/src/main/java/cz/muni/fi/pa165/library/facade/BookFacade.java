package cz.muni.fi.pa165.library.facade;

import cz.muni.fi.pa165.library.dto.BookDTO;
import java.util.List;

/**
 * @author Petr Janik 485122
 * @since 06.04.2020
 *
 * Front-facing interface for book methods
 */
public interface BookFacade {
    /**
     * method create new book and if valid add it to DB
     *
     * @param book book to add
     * @return id of the created book
     */
    long createBook(BookDTO book);

    /**
     * if book exists method remove it
     *
     * @param id of the book we wanna delete
     * @return id of the deleted book
     */
    long deleteBook(long id);

    /**
     *
     * @return all existing books
     */
    List<BookDTO> findAllBooks();

    /**
     * method gets all books having certain title
     *
     * @param title
     * @return list of books having certain title
     */
    List<BookDTO> findByTitle(String title);

    /**
     * method gets all books having certain author
     *
     * @param author
     * @return list of books having certain author
     */
    List<BookDTO> findByAuthor(String author);

    /**
     *
     * @param id
     * @return book having certain id
     */
    BookDTO findById(long id);
}
