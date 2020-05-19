package cz.muni.fi.pa165.library.services;

import cz.muni.fi.pa165.library.entities.Book;
import cz.muni.fi.pa165.library.repositories.BookRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Arrays;
import java.util.Optional;

/**
 * @author Katarína Hermanová
 * UČO 433511
 * Github katHermanova
 */
@RunWith(SpringRunner.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    public void testCreateBook() {
        Book book = new Book("Animal Farm", "George Orwell");

        Mockito.when(bookRepository.save(book)).thenReturn(book);

        Assert.assertNotNull(bookService.createBook(book));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindBookByAuthorNull() {
        bookService.findByAuthor(null).isEmpty();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindBookByTitleNull() {
        bookService.findByTitle(null).isEmpty();
    }

    @Test
    public void testFindBookByAuthor() {
        Book book = new Book("Animal Farm", "George Orwell");
        String author = book.getAuthor();

        Mockito.when(bookRepository.findAll()).thenReturn(Arrays.asList(book));

        Assert.assertEquals(Arrays.asList(book), bookService.findByAuthor(author));
    }

    @Test
    public void testFindMultipleBooksByAuthor() {
        Book book = new Book("Animal Farm", "George Orwell");
        Book book2 = new Book("Another Title", "George Orwell");
        String author = book.getAuthor();

        Mockito.when(bookRepository.findAll()).thenReturn(Arrays.asList(book, book2));

        Assert.assertEquals(Arrays.asList(book, book2), bookService.findByAuthor(author));
    }

    @Test
    public void testFindBookByTitle() {
        Book book = new Book("Animal Farm", "George Orwell");
        String title = book.getTitle();

        Mockito.when(bookRepository.findAll()).thenReturn(Arrays.asList(book));

        Assert.assertEquals(Arrays.asList(book), bookService.findByTitle(title));
    }

    @Test
    public void testFindMultipleBooksByTitle() {
        Book book = new Book("Animal Farm", "George Orwell");
        Book book2 = new Book("Animal Farm", "Another Author");
        String title = book.getTitle();

        Mockito.when(bookRepository.findAll()).thenReturn(Arrays.asList(book, book2));

        Assert.assertEquals(Arrays.asList(book, book2), bookService.findByTitle(title));
    }

    @Test
    public void testFindAll() {
        Book book = new Book("Animal Farm", "George Orwell");
        Book book2 = new Book("Another Title", "Another Author");

        Mockito.when(bookRepository.findAll()).thenReturn(Arrays.asList(book, book2));

        Assert.assertEquals(Arrays.asList(book, book2), bookService.findAll());
    }

    @Test
    public void testDeleteBook() {
        Book book = new Book("Animal Farm", "George Orwell");
        book.setId(1);

        Mockito.when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));

        Assert.assertEquals(book.getId(), bookService.deleteBook(book.getId()));
    }
}
