package cz.muni.fi.pa165.library.repositories;

import cz.muni.fi.pa165.library.entities.Book;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @author Katarína Hermanová
 * UČO 433511
 * Github katHermanova
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    private Book book1;
    private Book book2;

    @Test
    public void testAddOneBook() {
        setBook1();
        bookRepository.save(book1);
        Book foundBook = bookRepository.findAll().get(0);
        long foundCount = bookRepository.count();

        Assert.assertEquals(1, foundCount);
        Assert.assertEquals(book1, foundBook);
    }

    @Test
    public void testAddingTwoBooks() {
        setTwoBooks();
        bookRepository.save(book1);
        bookRepository.save(book2);
        long foundCount = bookRepository.count();
        List<Book> foundBooks = bookRepository.findAll();

        Assert.assertEquals(2, foundCount);
        Assert.assertEquals(foundBooks, Arrays.asList(book1, book2));
    }

    @Test
    public void testAddingTwoBooksInList() {
        setTwoBooks();
        bookRepository.saveAll(Arrays.asList(book1, book2));
        long foundCount = bookRepository.count();
        List<Book> foundBooks = bookRepository.findAll();

        Assert.assertEquals(2, foundCount);
        Assert.assertEquals(foundBooks, Arrays.asList(book1, book2));
    }

    @Test
    public void testGetBookById() {
        setBook1();
        bookRepository.save(book1);
        long foundId = bookRepository.findById(book1.getId()).get().getId();

        Assert.assertEquals(book1.getId(), foundId);
    }

    @Test
    public void testDeletingBook() {
        setTwoBooks();
        bookRepository.saveAll(Arrays.asList(book1, book2));
        bookRepository.delete(book1);
        long foundCount = bookRepository.count();
        List<Book> foundBooks = bookRepository.findAll();

        Assert.assertEquals(1, foundCount);
        Assert.assertEquals(foundBooks, Arrays.asList(book2));
    }

    @Test
    public void testDeletingBookInList() {
        setTwoBooks();
        bookRepository.saveAll(Arrays.asList(book1, book2));
        bookRepository.deleteAll(Arrays.asList(book1));
        long foundCount = bookRepository.count();
        List<Book> foundBooks = bookRepository.findAll();

        Assert.assertEquals(1, foundCount);
        Assert.assertEquals(foundBooks, Arrays.asList(book2));
    }

    private void setBook1() {
        book1 = new Book();
        book1.setTitle("Animal Farm");
        book1.setAuthor("George Orwell");
    }

    private void setTwoBooks() {
        setBook1();
        book2 = new Book();
        book2.setTitle("1984");
        book2.setAuthor("George Orwell");
    }
}
