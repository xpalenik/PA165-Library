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
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

/** @author Martin Páleník 359817 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class LoanFacadeImplTest {

    @Mock
    private SingleLoanService singleLoanService;

    @Mock
    private MappingService mappingService;

    @InjectMocks
    private LoanFacadeImpl loanFacadeImpl;

    @Test
    public void testBorrowBook() {
        final long fake_id = 43252343;
        
        Mockito.when(
                singleLoanService.createSingleLoan(
                        any()
                )
        ).thenReturn(fake_id);

        Assert.assertEquals(
                fake_id,
                loanFacadeImpl.borrowBook(new SingleLoanDTO())
        );
    }

    @Test
    public void testReturnBook() {
        Optional<SingleLoan> optionalSingleLoan
                = Optional.of(new SingleLoan());

        Mockito.when(
                singleLoanService.findById(
                        anyLong()
                )
        ).thenReturn(optionalSingleLoan);

        loanFacadeImpl.returnBook(new SingleLoanDTO());
    }

    @Test
    public void testGetSingleLoanById() {
        Optional<SingleLoan> optionalSingleLoan
                = Optional.of(new SingleLoan());

        Mockito.when(
                singleLoanService.findById(
                        anyLong()
                )
        ).thenReturn(optionalSingleLoan);

        Mockito.when(
                mappingService.mapTo(
                        optionalSingleLoan.get(),
                        SingleLoanDTO.class)
        ).thenReturn(new SingleLoanDTO());

        final long fake_id = 432432;

        Assert.assertNotNull(loanFacadeImpl.getSingleLoanById(fake_id));
    }

    @Test
    public void testGetLoansForUser() {
        loanFacadeImpl.getLoansForUser(new UserDTO());
    }

    @Test
    public void testGetLoansForBook() {
        loanFacadeImpl.getLoansForBook(new BookDTO());
    }

    @Test
    public void testGetAllSingleLoans() {
        loanFacadeImpl.getAllSingleLoans();
    }

    @Test
    public void testCount() {
        loanFacadeImpl.count();
    }

    @Test
    public void testDeleteById() {
        final long fake_id = 34325345;
        loanFacadeImpl.deleteById(fake_id);
    }
}