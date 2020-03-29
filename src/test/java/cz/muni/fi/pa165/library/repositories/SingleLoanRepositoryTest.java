package cz.muni.fi.pa165.library.repositories;

import cz.muni.fi.pa165.library.entities.Book;
import cz.muni.fi.pa165.library.entities.User;
import cz.muni.fi.pa165.library.entities.SingleLoan;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Petr Janik 485122
 * @since 29.03.2020
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class SingleLoanRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SingleLoanRepository singleLoanRepository;

    @Test
    public void createSingleLoan() {
        SingleLoan singleLoan = new SingleLoan();

        Book book = new Book();
        book.setAuthor("George Orwell");
        book.setTitle("Animal farm");
        entityManager.persist(book);

        User user = new User();
        user.setFirstName("Peter");
        user.setLastName("Griffin");
        user.setEmail("mail@mail.com");
        user.setPassword("password");

        entityManager.persist(book);
        entityManager.persist(user);

        singleLoan.setBook(book);
        singleLoan.setUser(user);

        entityManager.persist(singleLoan);

        List<SingleLoan> singleLoans = singleLoanRepository.findAll();

        assertThat(singleLoans, CoreMatchers.hasItems(singleLoan));
    }
}
