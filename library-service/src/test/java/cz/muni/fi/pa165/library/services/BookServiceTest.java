package cz.muni.fi.pa165.library.services;

import cz.muni.fi.pa165.library.entities.Book;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Arrays;

/**
 * @author Katarína Hermanová
 * UČO 433511
 * Github katHermanova
 */
//TODO cover BookService with tests
@RunWith(SpringRunner.class)
@DataJpaTest
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Test
    public void testCreateBook() {
        Book book = new Book("Animal Farm", "George Orwell");

        Assert.assertNotNull(bookService.createBook(book));
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
        Book book = new Book("Animal Farm", "George Orwell");
        String author = book.getAuthor();

        bookService.createBook(book);

        Assert.assertEquals(Arrays.asList(book), bookService.findByAuthor(author));
    }

    @Test
    public void testFindMultipleBooksByAuthor() {
        Book book = new Book("Animal Farm", "George Orwell");
        Book book2 = new Book("Another Title", "George Orwell");
        String author = book.getAuthor();

        bookService.createBook(book);
        bookService.createBook(book2);

        Assert.assertEquals(Arrays.asList(book, book2), bookService.findByAuthor(author));
    }

    @Test
    public void testFindBookByTitle() {
        Book book = new Book("Animal Farm", "George Orwell");
        String title = book.getTitle();

        bookService.createBook(book);

        Assert.assertEquals(Arrays.asList(book), bookService.findByTitle(title));
    }

    @Test
    public void testFindMultipleBooksByTitle() {
        Book book = new Book("Animal Farm", "George Orwell");
        Book book2 = new Book("Animal Farm", "Another Author");
        String title = book.getTitle();

        bookService.createBook(book);
        bookService.createBook(book2);

        Assert.assertEquals(Arrays.asList(book, book2), bookService.findByTitle(title));
    }

    @Test
    public void testFindAll() {
        Book book = new Book("Animal Farm", "George Orwell");
        Book book2 = new Book("Another Title", "Another Author");

        bookService.createBook(book);
        bookService.createBook(book2);

        Assert.assertEquals(Arrays.asList(book, book2), bookService.findAll());
    }

    @Test
    public void testDeleteBook() {
        Book book = new Book("Animal Farm", "George Orwell");
        Book book2 = new Book("Another Title", "Another Author");

        bookService.createBook(book);
        bookService.createBook(book2);

        Assert.assertEquals(2, bookService.findAll().size());
        Assert.assertEquals(book.getId(), bookService.deleteBook(book.getId()));
        Assert.assertEquals(Arrays.asList(book2), bookService.findAll());
    }
}
