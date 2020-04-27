package cz.muni.fi.pa165.library.services;

import cz.muni.fi.pa165.library.entities.Book;
import cz.muni.fi.pa165.library.entities.SingleLoan;
import cz.muni.fi.pa165.library.entities.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDateTime;

/** @author Martin Páleník 359817 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class SingleLoanServiceTest {

    private SingleLoan singleLoan;
    private Book book;
    private User user;

    @Autowired
    private SingleLoanService singleLoanService;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

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

    @Test
    public void testCreateSingleLoan(){
        setBook();
        bookService.createBook(book);

        setUser();
        userService.addUser(user);

        singleLoan = new SingleLoan();
        singleLoan.setBook(book);
        singleLoan.setUser(user);
        singleLoan.setRegisteredAt(LocalDateTime.now());

        long created_id = singleLoanService.createSingleLoan(singleLoan);
        Assert.assertNotNull(created_id);
    }
}