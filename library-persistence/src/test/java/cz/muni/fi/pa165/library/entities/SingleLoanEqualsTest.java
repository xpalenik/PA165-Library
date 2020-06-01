package cz.muni.fi.pa165.library.entities;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.persistence.EntityManager;
import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SingleLoanEqualsTest {
    @Autowired
    private EntityManager em;

    @Test
    public void testTransientSingleLoansNotEqual() {
        Book book1 = new Book("Title1", "Author1");
        Book book2 = new Book("Title2", "Author2");
        User user1 = new User("FirstName1", "LastName1", "FL1@mail.com", true);
        User user2 = new User("FirstName2", "LastName2", "FL2@mail.com", true);
        user1.setPasswordHash("hash1");
        user2.setPasswordHash("hash2");
        em.persist(book1);
        em.persist(book2);
        em.persist(user1);
        em.persist(user2);
        SingleLoan singleLoan1 = new SingleLoan(book1, user1, LocalDateTime.now());
        SingleLoan singleLoan2 = new SingleLoan(book2, user2, LocalDateTime.now());
        Assert.assertFalse(singleLoan1.equals(singleLoan2));
        em.persist(singleLoan1);
        em.persist(singleLoan2);
        Assert.assertFalse(singleLoan1.equals(singleLoan2));
    }

    @Test
    public void testManagedDifferentSingleLoansNotEqual() {
        Book book1 = new Book("Title1", "Author1");
        Book book2 = new Book("Title2", "Author2");
        User user1 = new User("FirstName1", "LastName1", "FL1@mail.com", true);
        User user2 = new User("FirstName2", "LastName2", "FL2@mail.com", true);
        user1.setPasswordHash("hash1");
        user2.setPasswordHash("hash2");
        em.persist(book1);
        em.persist(book2);
        em.persist(user1);
        em.persist(user2);
        SingleLoan singleLoan1 = new SingleLoan(book1, user1, LocalDateTime.now());
        SingleLoan singleLoan2 = new SingleLoan(book2, user2, LocalDateTime.now());
        em.persist(singleLoan1);
        em.persist(singleLoan2);
        Assert.assertFalse((em.find(SingleLoan.class, singleLoan1.getId())).equals(em.find(SingleLoan.class, singleLoan2.getId())));
    }

    @Test
    public void testManagedSameSingleLoansEqual() {
        Book book1 = new Book("Title1", "Author1");
        User user1 = new User("FirstName1", "LastName1", "FL1@mail.com", true);
        user1.setPasswordHash("hash1");
        em.persist(book1);
        em.persist(user1);
        SingleLoan singleLoan1 = new SingleLoan(book1, user1, LocalDateTime.now());
        em.persist(singleLoan1);
        SingleLoan singleLoan2 = em.find(SingleLoan.class, singleLoan1.getId());
        SingleLoan singleLoan3 = em.find(SingleLoan.class, singleLoan1.getId());
        Assert.assertTrue(singleLoan2.equals(singleLoan3));
    }

    @Test
    public void testDetachedAndMangedSingleLoanIsTheSame() {
        Book book1 = new Book("Title1", "Author1");
        User user1 = new User("FirstName1", "LastName1", "FL1@mail.com", true);
        user1.setPasswordHash("hash1");
        em.persist(book1);
        em.persist(user1);
        SingleLoan singleLoan1 = new SingleLoan(book1, user1, LocalDateTime.now());
        em.persist(singleLoan1);
        em.detach(singleLoan1);
        SingleLoan singleLoan2 = em.find(SingleLoan.class, singleLoan1.getId());
        Assert.assertTrue(singleLoan1.equals(singleLoan2));
    }

    @Test
    public void testReAttachedAndManagedSingleLoanIsTheSame() {
        Book book1 = new Book("Title1", "Author1");
        User user1 = new User("FirstName1", "LastName1", "FL1@mail.com", true);
        user1.setPasswordHash("hash1");
        em.persist(book1);
        em.persist(user1);
        SingleLoan singleLoan1 = new SingleLoan(book1, user1, LocalDateTime.now());
        em.persist(singleLoan1);
        SingleLoan singleLoan2 = em.merge(singleLoan1);
        Assert.assertTrue(singleLoan1.equals(singleLoan2));
    }
}
