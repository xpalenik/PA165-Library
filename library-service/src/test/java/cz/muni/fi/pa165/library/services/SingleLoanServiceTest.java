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

/** @author Martin Páleník 359817 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class SingleLoanServiceTest {

    @Autowired
    private SingleLoanService singleLoanService;

    @Autowired
    private TestEntityManager entityManager;

    private SingleLoan singleLoan;
    private Book book;
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
}