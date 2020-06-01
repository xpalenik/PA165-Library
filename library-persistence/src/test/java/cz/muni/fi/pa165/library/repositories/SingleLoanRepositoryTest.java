package cz.muni.fi.pa165.library.repositories;

import cz.muni.fi.pa165.library.entities.Book;
import cz.muni.fi.pa165.library.entities.User;
import cz.muni.fi.pa165.library.entities.SingleLoan;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

/**
 * @author Petr Janik 485122 && Katarina Hermanova 433511
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
    public void createSingleLoanAndDeletingIt() {
        Book book = new Book("Animal farm", "George Orwell");
        entityManager.persist(book);

        User user = new User("Peter", "Griffin", "mail@mail.com", false);
        user.setPasswordHash("password");
        entityManager.persist(user);

        SingleLoan singleLoan = new SingleLoan(book, user, LocalDateTime.of(2020, 1, 1, 12, 0));
        singleLoanRepository.save(singleLoan);

        Assert.assertNotNull(singleLoan.getId());
        Assert.assertTrue(singleLoanRepository.existsById(singleLoan.getId()));
        Assert.assertEquals(Arrays.asList(singleLoan), singleLoanRepository.findAll());
        Assert.assertEquals(1, singleLoanRepository.count());

        singleLoanRepository.delete(singleLoan);

        Assert.assertEquals(Arrays.asList(), singleLoanRepository.findAll());
    }

    @Test
    public void multipleSingleLoansForUserAndDeletingThem() {
        Book animalFarm = new Book("Animal farm", "George Orwell");
        Book book1984 = new Book("1984", "George Orwell");
        entityManager.persist(animalFarm);
        entityManager.persist(book1984);

        User user = new User("Peter", "Griffin", "mail@mail.com", false);
        user.setPasswordHash("password");
        entityManager.persist(user);

        SingleLoan singleLoan = new SingleLoan(animalFarm, user, LocalDateTime.of(2020, 1, 1, 12, 0));
        SingleLoan secondLoan = new SingleLoan(book1984, user, LocalDateTime.of(2020, 1, 1, 12, 0));
        entityManager.persist(singleLoan);
        entityManager.persist(secondLoan);

        Assert.assertNotNull(singleLoan.getId());
        Assert.assertNotNull(secondLoan.getId());
        Assert.assertTrue(singleLoanRepository.existsById(singleLoan.getId()));
        Assert.assertTrue(singleLoanRepository.existsById(secondLoan.getId()));
        Assert.assertEquals(Arrays.asList(singleLoan, secondLoan), singleLoanRepository.findAll());
        Assert.assertEquals(2, singleLoanRepository.count());

        singleLoanRepository.deleteAll();

        Assert.assertEquals(Arrays.asList(), singleLoanRepository.findAll());
    }

    @Test(expected = DataAccessException.class)
    public void saveNull(){
        singleLoanRepository.save(null);
    }

    @Test(expected = DataAccessException.class)
    public void saveAllNull(){
        singleLoanRepository.saveAll(null);
    }

    @Test(expected = DataAccessException.class)
    public void testDeleteNull() {
        singleLoanRepository.delete(null);
    }

    @Test(expected = DataAccessException.class)
    public void testDeleteAllNull() {
        singleLoanRepository.deleteAll(null);
    }

    @Test(expected = DataAccessException.class)
    public void testDeleteByNonExistingId() {
        singleLoanRepository.deleteById(Long.MAX_VALUE);
    }

    @Test(expected = DataAccessException.class)
    public void testFindByIdNull() {
        singleLoanRepository.findById(null);
    }

    @Test(expected = DataAccessException.class)
    public void testDeleteByIdNull() {
        singleLoanRepository.deleteById(null);
    }

    @Test(expected = DataAccessException.class)
    public void testExistsByIdNull() {
        singleLoanRepository.existsById(null);
    }

    @Test
    public void testFindByNonExistingId() {
        Assert.assertEquals(Optional.empty(), singleLoanRepository.findById(Long.MAX_VALUE));
    }
}
