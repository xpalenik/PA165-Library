package cz.muni.fi.pa165.library.facade;

import cz.muni.fi.pa165.library.dto.BookDTO;
import cz.muni.fi.pa165.library.entities.Book;
import cz.muni.fi.pa165.library.services.BookService;
import cz.muni.fi.pa165.library.services.MappingService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Arrays;

/**
 * @author Katarína Hermanová
 * UČO 433511
 * Github katHermanova
 */
@RunWith(SpringRunner.class)
public class BookFacadeTest {
    private Book book;
    private Book book2;
    private BookDTO bookDTO;
    private BookDTO bookDTO2;

    @InjectMocks
    private BookFacadeImpl bookFacade;

    @Mock
    private BookService bookService;

    @Mock
    private MappingService mappingService;

    @Test
    public void testFindById() {
        setBook();

        Mockito.when(
                bookService.findById(book.getId())
        ).thenReturn(
                book
        );

        Mockito.when(
                mappingService.mapTo(book, BookDTO.class)
        ).thenReturn(
                bookDTO
        );

        Assert.assertEquals(bookDTO, bookFacade.findById(book.getId()));
    }

    @Test
    public void testFindByTitle() {
        setBook();

        Mockito.when(
                bookService.findByTitle(book.getTitle())
        ).thenReturn(
                Arrays.asList(book)
        );

        Mockito.when(
                mappingService.mapTo(book, BookDTO.class)
        ).thenReturn(
                bookDTO
        );

        Assert.assertEquals(Arrays.asList(bookDTO), bookFacade.findByTitle(book.getTitle()));
    }

    @Test
    public void testFindByAuthor() {
        setBook();

        Mockito.when(
                bookService.findByAuthor(book.getAuthor())
        ).thenReturn(
                Arrays.asList(book)
        );

        Mockito.when(
                mappingService.mapTo(book, BookDTO.class)
        ).thenReturn(
                bookDTO
        );

        Assert.assertEquals(Arrays.asList(bookDTO), bookFacade.findByAuthor(book.getAuthor()));
    }

    @Test
    public void testFindAll() {
        setTwoBooks();

        Mockito.when(
                bookService.findAll()
        ).thenReturn(
                Arrays.asList(book, book2))
        ;

        Mockito.when(
                mappingService.mapTo(book, BookDTO.class)
        ).thenReturn(
                bookDTO
        );

        Mockito.when(
                mappingService.mapTo(book2, BookDTO.class)
        ).thenReturn(
                bookDTO2
        );

        Assert.assertEquals(Arrays.asList(bookDTO, bookDTO2), bookFacade.findAllBooks());
    }

    @Test
    public void testAddUser() {
        setBook();

        Mockito.when(
                bookService.createBook(
                        mappingService.mapTo(bookDTO, Book.class)
                )
        ).thenReturn(
                book.getId()
        );

        Assert.assertEquals(book.getId(), bookFacade.createBook(bookDTO));
    }

    private void setBook() {
        book = new Book("Animal Farm", "George Orwell");
        book.setId(123);

        bookDTO = new BookDTO(book.getId(), "Animal Farm", "George Orwell");
    }

    private void setTwoBooks() {
        setBook();

        book2 = new Book("Another Title", "Another Author");
        book2.setId(456);

        bookDTO2 = new BookDTO(book2.getId(), "Another Title", "Another Author");
    }
}
