package cz.muni.fi.pa165.library.facade;

import cz.muni.fi.pa165.library.dto.BookDTO;

import java.util.List;

/**
 * @author Petr Janik 485122
 * @since 06.04.2020
 */
//TODO implement methods
public class BookFacadeImpl implements BookFacade {
    @Override
    public long createBook(BookDTO book) {
        return 0;
    }

    @Override
    public long deleteBook(long id) {
        return 0;
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return null;
    }

    @Override
    public List<BookDTO> getBooksByTitle(String title) {
        return null;
    }

    @Override
    public List<BookDTO> getBooksByAuthor(String author) {
        return null;
    }

    @Override
    public BookDTO getBookById(long id) {
        return null;
    }
}
