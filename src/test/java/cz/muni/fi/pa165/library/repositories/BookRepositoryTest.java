package cz.muni.fi.pa165.library.repositories;

import cz.muni.fi.pa165.library.entities.Book;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void findByEmail() {
        Book book1 = new Book();
        book1.setTitle("Animal Farm");
        book1.setAuthor("George Orwell");
        entityManager.persist(book1);

        Book book2 = new Book();
        book2.setTitle("1984");
        book2.setAuthor("George Orwell");
        entityManager.persist(book2);

        List<Book> foundBooks = bookRepository.findAll();

        assertThat(foundBooks, CoreMatchers.hasItems(book1, book2));
    }
}
