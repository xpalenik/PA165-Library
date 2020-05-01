package cz.muni.fi.pa165.library.services;

import cz.muni.fi.pa165.library.entities.Book;
import cz.muni.fi.pa165.library.entities.SingleLoan;
import cz.muni.fi.pa165.library.entities.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

/** @author Martin Páleník 359817 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class SingleLoanServiceTest {

    @Autowired
    private SingleLoanService singleLoanService;

    @Autowired
    private TestEntityManager entityManager;

    private SingleLoan singleLoan;
    private SingleLoan singleLoan2;
    private Book book;
    private Book book2;
    private User user;

    private void setBook() {
        book = new Book();
        book.setTitle("Animal Farm");
        book.setAuthor("George Orwell");
    }

    private void setUser() {
        user = new User();
        user.setFirstName("Martin");
        user.setLastName("Páleník");
        user.setEmail("359817@mail.muni.cz");
        user.setPasswordHash("H4SH");
    }

    private void setSingleLoan() {
        setBook();
        book.setId(
                (long) entityManager.persistAndGetId(book)
        );

        setUser();
        user.setId(
                (long) entityManager.persistAndGetId(user)
        );

        singleLoan = new SingleLoan();
        singleLoan.setBook(book);
        singleLoan.setUser(user);
        singleLoan.setRegisteredAt(LocalDateTime.MIN);
        singleLoan.setReturnedAt(LocalDateTime.MAX);
        singleLoan.setReturnCondition("damaged");
    }

    private void setTwoSingleLoans() {
        setSingleLoan();
        book2 = new Book("Title", "Author");
        book2.setId((long)entityManager.persistAndGetId(book2));
        singleLoan2 = new SingleLoan();
        singleLoan2.setBook(book2);
        singleLoan2.setUser(user);
        singleLoan2.setRegisteredAt(LocalDateTime.MIN);
        singleLoan2.setReturnedAt(LocalDateTime.MAX);
        singleLoan2.setReturnCondition("damaged");
    }

    @Test
    public void testCreateSingleLoan(){
        setSingleLoan();

        long created_id = singleLoanService.createSingleLoan(singleLoan);
        Assert.assertNotNull(created_id);

        SingleLoan justSaved = singleLoanService.findById(created_id).get();
        Assert.assertEquals(justSaved, singleLoan);

        // since SingleLoan.equals() might be broken
        // I also provide some explicit tests

        Assert.assertEquals(
                justSaved.getReturnCondition(),
                "damaged"
        );

        Assert.assertEquals(
                justSaved.getUser().getEmail(),
                "359817@mail.muni.cz"
        );

        Assert.assertEquals(
                justSaved.getBook().getTitle(),
                "Animal Farm"
        );
    }

    @Test
    public void testFindById() {
        setSingleLoan();

        singleLoanService.createSingleLoan(singleLoan);

        Assert.assertEquals(Optional.of(singleLoan), singleLoanService.findById(singleLoan.getId()));
    }

    @Test
    public void testFindAll() {
        setTwoSingleLoans();

        singleLoanService.createSingleLoan(singleLoan);
        singleLoanService.createSingleLoan(singleLoan2);

        Assert.assertEquals(Arrays.asList(singleLoan, singleLoan2), singleLoanService.findAll());
    }

    @Test
    public void testCount() {
        setTwoSingleLoans();

        singleLoanService.createSingleLoan(singleLoan);
        singleLoanService.createSingleLoan(singleLoan2);

        Assert.assertEquals(2, (long)singleLoanService.count()); //change signature of method from Long to long
    }

    @Test
    public void testDeleteById() {
        setTwoSingleLoans();

        singleLoanService.createSingleLoan(singleLoan);
        singleLoanService.createSingleLoan(singleLoan2);

        Assert.assertEquals(2, (long)singleLoanService.count());
        singleLoanService.deleteById(singleLoan.getId());
        Assert.assertEquals(Arrays.asList(singleLoan2), singleLoanService.findAll());
    }

    @Test
    public void testGetLoansForUser() {
        setTwoSingleLoans();

        singleLoanService.createSingleLoan(singleLoan);
        singleLoanService.createSingleLoan(singleLoan2);

        Assert.assertEquals(Arrays.asList(singleLoan, singleLoan2), singleLoanService.getLoansForUser(user));
    }

    @Test
    public void testGetLoansForBook() {
        setSingleLoan();

        User user2 = new User("M", "Pal", "mp@mail.com", false);
        user2.setId((long) entityManager.persistAndGetId(user2));

        singleLoan2 = new SingleLoan();
        singleLoan2.setBook(book);
        singleLoan2.setUser(user2);
        singleLoan2.setRegisteredAt(LocalDateTime.MIN);
        singleLoan2.setReturnedAt(LocalDateTime.MAX);
        singleLoan2.setReturnCondition("damaged");

        singleLoanService.createSingleLoan(singleLoan);
        singleLoanService.createSingleLoan(singleLoan2);

        Assert.assertEquals(Arrays.asList(singleLoan, singleLoan2), singleLoanService.getLoansForBook(book));
    }

    @Test
    public void testReturnBook() {
        setSingleLoan();

        singleLoanService.createSingleLoan(singleLoan);
        singleLoanService.returnBook(singleLoan, LocalDateTime.MAX, "ok");

        Assert.assertEquals(LocalDateTime.MAX, singleLoan.getReturnedAt());
        Assert.assertEquals("ok", singleLoan.getReturnCondition());
    }
}