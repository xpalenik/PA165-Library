package cz.muni.fi.pa165.library.facade;

import cz.muni.fi.pa165.library.dto.BookDTO;
import cz.muni.fi.pa165.library.dto.SingleLoanDTO;
import cz.muni.fi.pa165.library.dto.UserDTO;
import cz.muni.fi.pa165.library.entities.Book;
import cz.muni.fi.pa165.library.entities.SingleLoan;
import cz.muni.fi.pa165.library.entities.User;
import cz.muni.fi.pa165.library.services.SingleLoanService;
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
public class LoanFacadeImplTest {

    SingleLoanDTO singleLoanInfo;
    private BookDTO bookDto;
    private UserDTO userDto;

    @Autowired
    private LoanFacadeImpl loanFacadeImpl;

    // not all service yet implemented by facade
    @Autowired
    private SingleLoanService singleLoanService;

    @Autowired
    private TestEntityManager entityManager;

    private void setBook() {
        bookDto = new BookDTO();
        bookDto.setTitle("Animal Farm");
        bookDto.setAuthor("George Orwell");
    }

    private void setUser() {
        userDto = new UserDTO();
        userDto.setFirstName("Martin");
        userDto.setLastName("Páleník");
        userDto.setEmail("359817@mail.muni.cz");
        userDto.setPasswordHash("H4SH");
    }

    private void setSingleLoanInfo(){
        singleLoanInfo = new SingleLoanDTO();
        singleLoanInfo.setRegisteredAt(LocalDateTime.now());
        singleLoanInfo.setReturnedAt(LocalDateTime.now());
        singleLoanInfo.setReturnCondition("damaged");

        setBook();
        singleLoanInfo.setBook(bookDto);

        setUser();
        singleLoanInfo.setUser(userDto);
    }

    @Test
    public void testBorrowBookByUser() {
        setSingleLoanInfo();

        Book book = new Book();
        book.setAuthor(bookDto.getAuthor());
        book.setTitle(bookDto.getTitle());

        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPasswordHash(userDto.getPasswordHash());
        user.setLibrarian(userDto.isLibrarian());

        singleLoanInfo.getBook().setId(
                (long) entityManager.persistAndGetId(book)
        );

        singleLoanInfo.getUser().setId(
                (long) entityManager.persistAndGetId(user)
        );

        long loan_id = loanFacadeImpl.borrowBook(singleLoanInfo);
        singleLoanInfo.setId(loan_id); // now singleLoanInfo matches the saved object
        SingleLoan justSaved = singleLoanService.findById(loan_id).get();

        Assert.assertNotNull(loan_id);

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

        /* Note
           It might not be a good idea to use Dozer
           to map singleLoanInfo to SingleLoan
           and compare with justSaved using equals()
           since Dozer or equals() might be broken.

           Therefore, I used the explicit assetions above.
         */
    }
}