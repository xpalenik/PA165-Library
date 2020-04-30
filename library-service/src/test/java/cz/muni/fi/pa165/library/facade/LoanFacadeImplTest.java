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

    SingleLoanDTO singleLoanDto;
    private BookDTO bookDto;
    private UserDTO userDto;

    @Autowired
    private LoanFacadeImpl loanFacadeImpl;

    @Autowired
    private SingleLoanService singleLoanService;

    @Autowired
    private TestEntityManager entityManager;

    private void setBookDto() {
        bookDto = new BookDTO();
        bookDto.setTitle("Animal Farm");
        bookDto.setAuthor("George Orwell");
    }

    private void setUserDto() {
        userDto = new UserDTO();
        userDto.setFirstName("Martin");
        userDto.setLastName("Páleník");
        userDto.setEmail("359817@mail.muni.cz");
        userDto.setPasswordHash("H4SH");
    }

    private void setSingleLoanInfo(){
        singleLoanDto = new SingleLoanDTO();
        singleLoanDto.setRegisteredAt(LocalDateTime.MIN);
        singleLoanDto.setReturnedAt(LocalDateTime.MAX);
        singleLoanDto.setReturnCondition("damaged");

        setBookDto();
        singleLoanDto.setBook(bookDto);

        setUserDto();
        singleLoanDto.setUser(userDto);
    }

    @Test
    public void testBorrowBook() {

    }
}