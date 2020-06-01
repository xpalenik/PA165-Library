package cz.muni.fi.pa165.library.services;

import cz.muni.fi.pa165.library.entities.Book;
import cz.muni.fi.pa165.library.entities.SingleLoan;
import cz.muni.fi.pa165.library.entities.User;
import cz.muni.fi.pa165.library.repositories.SingleLoanRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

/** @author Martin Páleník 359817 */

@RunWith(SpringRunner.class)
public class SingleLoanServiceTest {

    @Mock
    private SingleLoanRepository singleLoanRepository;

    @InjectMocks
    private SingleLoanService singleLoanService;

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
        book.setId(1);

        setUser();
        user.setId(2);

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
        book2.setId(3);
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

        Mockito.when(singleLoanRepository.save(singleLoan)).thenReturn(singleLoan);

        long created_id = singleLoanService.createSingleLoan(singleLoan);
        Assert.assertNotNull(created_id);

        Mockito.when(
                singleLoanRepository.findById(created_id)
        ).thenReturn(
                Optional.of(singleLoan)
        );

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

        Mockito.when(
                singleLoanRepository.findById(singleLoan.getId())
        ).thenReturn(
                Optional.of(singleLoan)
        );

        Assert.assertEquals(Optional.of(singleLoan), singleLoanService.findById(singleLoan.getId()));
    }

    @Test
    public void testFindAll() {
        setTwoSingleLoans();

        Mockito.when(
                singleLoanRepository.findAll()
        ).thenReturn(
                Arrays.asList(singleLoan, singleLoan2)
        );

        Assert.assertEquals(Arrays.asList(singleLoan, singleLoan2), singleLoanService.findAll());
    }

    @Test
    public void testGetLoansForUser() {
        setTwoSingleLoans();

        Mockito.when(
                singleLoanRepository.findAll()
        ).thenReturn(
                Arrays.asList(singleLoan, singleLoan2)
        );

        Assert.assertEquals(Arrays.asList(singleLoan, singleLoan2), singleLoanService.getLoansForUser(user));
    }

    @Test
    public void testGetLoansForBook() {
        setSingleLoan();

        User user2 = new User("M", "Pal", "mp@mail.com", false);
        user2.setPasswordHash("64HSQWL5");
        user2.setId(4);

        singleLoan2 = new SingleLoan();
        singleLoan2.setBook(book);
        singleLoan2.setUser(user2);
        singleLoan2.setRegisteredAt(LocalDateTime.MIN);
        singleLoan2.setReturnedAt(LocalDateTime.MAX);
        singleLoan2.setReturnCondition("damaged");

        Mockito.when(
                singleLoanRepository.findAll()
        ).thenReturn(
                Arrays.asList(singleLoan, singleLoan2)
        );

        Assert.assertEquals(Arrays.asList(singleLoan, singleLoan2), singleLoanService.getLoansForBook(book));
    }

    @Test
    public void testReturnBook() {
        setSingleLoan();

        singleLoanService.returnBook(singleLoan, LocalDateTime.MAX, "ok");

        Assert.assertEquals(LocalDateTime.MAX, singleLoan.getReturnedAt());
        Assert.assertEquals("ok", singleLoan.getReturnCondition());
    }
}