package cz.muni.fi.pa165.library.facade;

import cz.muni.fi.pa165.library.dto.BookDTO;
import cz.muni.fi.pa165.library.dto.SingleLoanDTO;
import cz.muni.fi.pa165.library.dto.UserDTO;
import cz.muni.fi.pa165.library.entities.SingleLoan;
import cz.muni.fi.pa165.library.services.MappingService;
import cz.muni.fi.pa165.library.services.SingleLoanService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

/** @author Martin Páleník 359817 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class LoanFacadeImplTest {

    private BookDTO bookDto;
    private UserDTO userDto;
    SingleLoanDTO singleLoanDto;

    @Mock
    private SingleLoanService singleLoanService;

    @Autowired
    @Mock
    private MappingService mappingService;

    @InjectMocks
    private LoanFacadeImpl loanFacadeImpl;

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

    private void setSingleLoanDto(){
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
        setSingleLoanDto();
        final long fake_id = 43252343;
        
        Mockito.when(
                singleLoanService.createSingleLoan(
                        any()
                )
        ).thenReturn(fake_id);

        Assert.assertEquals(
                fake_id,
                loanFacadeImpl.borrowBook(singleLoanDto)
        );
    }

    @Test
    public void testReturnBook() {
        setSingleLoanDto();
        Optional<SingleLoan> optionalSingleLoan
                = Optional.of(mappingService.mapTo(singleLoanDto, SingleLoan.class));

        Mockito.when(
                singleLoanService.findById(
                        anyLong()
                )
        ).thenReturn(optionalSingleLoan);

        loanFacadeImpl.returnBook(singleLoanDto);
    }
}