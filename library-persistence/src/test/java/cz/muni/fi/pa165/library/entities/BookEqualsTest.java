package cz.muni.fi.pa165.library.entities;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookEqualsTest {
    @Autowired
    private EntityManager em;

    @Test
    public void testTransientBooksNotEqual() {
        Book book1 = new Book("Title1", "Author1");
        Book book2 = new Book("Title2", "Author2");
        Assert.assertFalse(book1.equals(book2));
        em.persist(book1);
        em.persist(book2);
        Assert.assertFalse(book1.equals(book2));
    }

    @Test
    public void testManagedDifferentBooksNotEqual() {
        Book book1 = new Book("Title1", "Author1");
        Book book2 = new Book("Title2", "Author2");
        em.persist(book1);
        em.persist(book2);
        Assert.assertFalse((em.find(Book.class, book1.getId())).equals(em.find(Book.class, book2.getId())));
    }

    @Test
    public void testManagedSameBooksEqual() {
        Book book1 = new Book("Title1", "Author1");
        em.persist(book1);
        Book book2 = em.find(Book.class, book1.getId());
        Book book3 = em.find(Book.class, book1.getId());
        Assert.assertTrue(book2.equals(book3));
    }

    @Test
    public void testDetachedAndMangedBookIsTheSame() {
        Book book1 = new Book("Title1", "Author1");
        em.persist(book1);
        em.detach(book1);
        Book book2 = em.find(Book.class, book1.getId());
        Assert.assertTrue(book1.equals(book2));
    }

    @Test
    public void testReAttachedAndManagedBookIsTheSame() {
        Book book1 = new Book("Title1", "Author1");
        em.persist(book1);
        Book book2 = em.merge(book1);
        Assert.assertTrue(book1.equals(book2));
    }
}
