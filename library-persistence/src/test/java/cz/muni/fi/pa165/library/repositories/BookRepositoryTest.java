package cz.muni.fi.pa165.library.repositories;

import cz.muni.fi.pa165.library.entities.Book;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

    @Test
    public void testAddOneBook() {
        Book book1 = new Book("Animal Farm", "George Orwell");

        bookRepository.save(book1);

        Book foundBook = bookRepository.findAll().get(0);
        long foundCount = bookRepository.count();

        Assert.assertTrue(bookRepository.existsById(book1.getId()));
        Assert.assertEquals(1, foundCount);
        Assert.assertEquals(book1, foundBook);
    }

    @Test
    public void testAddingTwoBooks() {
        Book book1 = new Book("Animal Farm", "George Orwell");
        Book book2 = new Book("1984", "George Orwell");

        bookRepository.save(book1);
        bookRepository.save(book2);

        long foundCount = bookRepository.count();
        List<Book> foundBooks = bookRepository.findAll();

        Assert.assertEquals(2, foundCount);
        Assert.assertEquals(foundBooks, Arrays.asList(book1, book2));
    }

    @Test
    public void testAddingTwoBooksInList() {
        Book book1 = new Book("Animal Farm", "George Orwell");
        Book book2 = new Book("1984", "George Orwell");

        bookRepository.saveAll(Arrays.asList(book1, book2));

        long foundCount = bookRepository.count();
        List<Book> foundBooks = bookRepository.findAll();

        Assert.assertEquals(2, foundCount);
        Assert.assertEquals(foundBooks, Arrays.asList(book1, book2));
    }

    @Test
    public void testGetBookById() {
        Book book1 = new Book("Animal Farm", "George Orwell");
        bookRepository.save(book1);
        long foundId = bookRepository.findById(book1.getId()).get().getId();

        Assert.assertEquals(book1.getId(), foundId);
    }

    @Test
    public void testDeletingBook() {
        Book book1 = new Book("Animal Farm", "George Orwell");
        Book book2 = new Book("1984", "George Orwell");

        bookRepository.saveAll(Arrays.asList(book1, book2));
        bookRepository.delete(book1);

        long foundCount = bookRepository.count();
        List<Book> foundBooks = bookRepository.findAll();

        Assert.assertEquals(1, foundCount);
        Assert.assertEquals(foundBooks, Arrays.asList(book2));
    }

    @Test
    public void testDeletingBookInList() {
        Book book1 = new Book("Animal Farm", "George Orwell");
        Book book2 = new Book("1984", "George Orwell");

        bookRepository.saveAll(Arrays.asList(book1, book2));
        bookRepository.deleteAll(Arrays.asList(book1));

        long foundCount = bookRepository.count();
        List<Book> foundBooks = bookRepository.findAll();

        Assert.assertEquals(1, foundCount);
        Assert.assertEquals(foundBooks, Arrays.asList(book2));
    }

    @Test(expected = DataAccessException.class)
    public void saveNull(){
        bookRepository.save(null);
    }

    @Test(expected = DataAccessException.class)
    public void saveAllNull(){
        bookRepository.saveAll(null);
    }

    @Test(expected = DataAccessException.class)
    public void testDeleteNull() {
        bookRepository.delete(null);
    }

    @Test(expected = DataAccessException.class)
    public void testDeleteAllNull() {
        bookRepository.deleteAll(null);
    }

    @Test(expected = DataAccessException.class)
    public void testDeleteByNonExistingId() {
        bookRepository.deleteById(Long.MAX_VALUE);
    }

    @Test(expected = DataAccessException.class)
    public void testFindByIdNull() {
        bookRepository.findById(null);
    }

    @Test(expected = DataAccessException.class)
    public void testDeleteByIdNull() {
        bookRepository.deleteById(null);
    }

    @Test(expected = DataAccessException.class)
    public void testExistsByIdNull() {
        bookRepository.existsById(null);
    }

    @Test
    public void testFindByNonExistingId() {
        Assert.assertEquals(Optional.empty(), bookRepository.findById(Long.MAX_VALUE));
    }
}
