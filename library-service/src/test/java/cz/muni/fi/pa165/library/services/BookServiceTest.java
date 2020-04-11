package cz.muni.fi.pa165.library.services;

import cz.muni.fi.pa165.library.entities.Book;
import cz.muni.fi.pa165.library.repositories.BookRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
public class BookServiceTest {

    private Book book1;
    private Book book2;
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Before
    public void init() {
        bookService = new BookService(bookRepository);
    }

    @Test
    public void testFindBookByAuthorNull() {
        Assert.assertTrue(bookService.findByAuthor(null).isEmpty());
    }

    @Test
    public void testFindBookByTitleNull() {
        Assert.assertTrue(bookService.findByTitle(null).isEmpty());
    }

    @Test
    public void testFindBookByAuthor() {
        setBook1();
        String author = book1.getAuthor();

        Mockito.when(bookRepository.findAll())
                .thenReturn(Arrays.asList(book1));

        Assert.assertEquals(Arrays.asList(book1), bookService.findByAuthor(author));
    }

    @Test
    public void testFindMultipleBooksByAuthor() {
        setBook1();
        book2 = new Book();
        book2.setTitle("Another Title");
        book2.setAuthor("George Orwell");
        String author = book1.getAuthor();

        Mockito.when(bookRepository.findAll())
                .thenReturn(Arrays.asList(book1, book2));

        Assert.assertEquals(Arrays.asList(book1, book2), bookService.findByAuthor(author));
    }

    @Test
    public void testFindBookByTitle() {
        setBook1();
        String title = book1.getTitle();

        Mockito.when(bookRepository.findAll())
                .thenReturn(Arrays.asList(book1));

        Assert.assertEquals(Arrays.asList(book1), bookService.findByTitle(title));
    }

    @Test
    public void testFindMultipleBooksByTitle() {
        setBook1();
        book2 = new Book();
        book2.setTitle("Animal Farm");
        book2.setAuthor("Another Author");
        String title = book1.getTitle();

        Mockito.when(bookRepository.findAll())
                .thenReturn(Arrays.asList(book1, book2));

        Assert.assertEquals(Arrays.asList(book1, book2), bookService.findByTitle(title));
    }

    private void setBook1() {
        book1 = new Book();
        book1.setTitle("Animal Farm");
        book1.setAuthor("George Orwell");
    }
}
