package cz.muni.fi.pa165.library.sampleData;

import cz.muni.fi.pa165.library.entities.Book;
import cz.muni.fi.pa165.library.entities.SingleLoan;
import cz.muni.fi.pa165.library.entities.User;
import cz.muni.fi.pa165.library.services.BookService;
import cz.muni.fi.pa165.library.services.SingleLoanService;
import cz.muni.fi.pa165.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

/**
 * @author Katarína Hermanová
 * UČO 433511
 * Github katHermanova
 *
 * Creates initial data
 */
@Component
@Transactional
public class SampleData {
    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @Autowired
    private SingleLoanService singleLoanService;

    private Book book1 = new Book("Witcher", "Andrzej Sapkowski");
    private Book book2 = new Book("Witcher - Sword of destiny", "Andrzej Sapkowski");
    private Book book3 = new Book("Road to the north", "Virginie Grimaldi");
    private Book book4 = new Book("Petr and Lucy", "Romain Rolland");
    private Book book5 = new Book("The Little Prince", "Antoine de Saint-Exupéry");
    private Book book6 = new Book("The Great Gatsby", "Francis Scott Fitzgerald");

    private User user1 = new User("Katarina", "Hermanova", "kHermano@mail.com", true);
    private User user2 = new User("Martin", "Palenik", "mPalenik@mail.com", true);
    private User user3 = new User("New", "User", "onlyUser@mail.com", false);
    private User user4 = new User("New", "Admin", "admin@mail.com", true);

    public void loadData() {
        bookService.createBook(book1);
        bookService.createBook(book2);
        bookService.createBook(book3);
        bookService.createBook(book4);
        bookService.createBook(book5);
        bookService.createBook(book6);

        userService.addUser(user1, "kHermanoPass");
        userService.addUser(user2, "mPalenikPass");
        userService.addUser(user3, "onlyUserPass");
        userService.addUser(user4, "admin");

        singleLoanService.createSingleLoan(new SingleLoan(book1, user2, LocalDateTime.now()));
        singleLoanService.createSingleLoan(new SingleLoan(book2, user2, LocalDateTime.now()));
        singleLoanService.createSingleLoan(new SingleLoan(book3, user2, LocalDateTime.now()));
        singleLoanService.createSingleLoan(new SingleLoan(book4, user2, LocalDateTime.now()));
        singleLoanService.createSingleLoan(new SingleLoan(book5, user3, LocalDateTime.now()));
        singleLoanService.createSingleLoan(new SingleLoan(book6, user3, LocalDateTime.now()));
    }
}
