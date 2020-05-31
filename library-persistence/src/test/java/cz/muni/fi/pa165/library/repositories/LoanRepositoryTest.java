package cz.muni.fi.pa165.library.repositories;

import cz.muni.fi.pa165.library.entities.Book;
import cz.muni.fi.pa165.library.entities.Loan;
import cz.muni.fi.pa165.library.entities.SingleLoan;
import cz.muni.fi.pa165.library.entities.User;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @author Petr Janik 485122
 * @since 29.03.2020
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class LoanRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private SingleLoanRepository singleLoanRepository;

    @Test
    public void createLoan() {
        Book animalFarm = createTestBookAnimalFarm();
        Book book1984 = createTestBook1984();
        entityManager.persist(animalFarm);
        entityManager.persist(book1984);

        User user = createTestUserPeter();
        entityManager.persist(user);

        SingleLoan singleLoan = new SingleLoan();
        singleLoan.setBook(animalFarm);
        singleLoan.setUser(user);
        singleLoan.setRegisteredAt(LocalDateTime.of(2020, 1, 1, 12, 0));
        entityManager.persist(singleLoan);

        SingleLoan secondLoan = new SingleLoan();
        secondLoan.setBook(book1984);
        secondLoan.setUser(user);
        secondLoan.setRegisteredAt(LocalDateTime.of(2020, 1, 1, 12, 0));
        entityManager.persist(secondLoan);

        Loan loan = new Loan();
        loan.setLoans(Arrays.asList(singleLoan, secondLoan));
        entityManager.persist(loan);


        MatcherAssert.assertThat(loanRepository.findAll(), CoreMatchers.hasItems(loan));
        MatcherAssert.assertThat(singleLoanRepository.findAll(), CoreMatchers.hasItems(singleLoan, secondLoan));
    }

    @Test(expected = DataAccessException.class)
    public void saveNullThrowsDataAccessException(){
        loanRepository.save(null);
    }

    private Book createTestBookAnimalFarm() {
        Book book = new Book();
        book.setAuthor("George Orwell");
        book.setTitle("Animal farm");
        return book;
    }

    private Book createTestBook1984() {
        Book book = new Book();
        book.setAuthor("George Orwell");
        book.setTitle("1984");
        return book;
    }

    private User createTestUserPeter() {
        User user = new User();
        user.setFirstName("Peter");
        user.setLastName("Griffin");
        user.setEmail("mail@mail.com");
        user.setPasswordHash("password");
        return user;
    }
}
