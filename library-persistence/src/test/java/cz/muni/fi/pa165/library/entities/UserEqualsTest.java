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
public class UserEqualsTest {
    @Autowired
    private EntityManager em;

    @Test
    public void testTransientUsersNotEqual() {
        User user1 = new User("FirstName1", "LastName1", "FL1@mail.com", true);
        User user2 = new User("FirstName2", "LastName2", "FL2@mail.com", true);
        user1.setPasswordHash("hash1");
        user2.setPasswordHash("hash2");
        Assert.assertFalse(user1.equals(user2));
        em.persist(user1);
        em.persist(user2);
        Assert.assertFalse(user1.equals(user2));
    }

    @Test
    public void testManagedDifferentUsersNotEqual() {
        User user1 = new User("FirstName1", "LastName1", "FL1@mail.com", true);
        User user2 = new User("FirstName2", "LastName2", "FL2@mail.com", true);
        user1.setPasswordHash("hash1");
        user2.setPasswordHash("hash2");
        em.persist(user1);
        em.persist(user2);
        Assert.assertFalse((em.find(User.class, user1.getId())).equals(em.find(User.class, user2.getId())));
    }

    @Test
    public void testManagedSameUsersEqual() {
        User user1 = new User("FirstName1", "LastName1", "FL1@mail.com", true);
        user1.setPasswordHash("hash1");
        em.persist(user1);
        User user2 = em.find(User.class, user1.getId());
        User user3 = em.find(User.class, user1.getId());
        Assert.assertTrue(user2.equals(user3));
    }

    @Test
    public void testDetachedAndMangedUserIsTheSame() {
        User user1 = new User("FirstName1", "LastName1", "FL1@mail.com", true);
        user1.setPasswordHash("hash1");
        em.persist(user1);
        em.detach(user1);
        User user2 = em.find(User.class, user1.getId());
        Assert.assertTrue(user1.equals(user2));
    }

    @Test
    public void testReAttachedAndManagedUserIsTheSame() {
        User user1 = new User("FirstName1", "LastName1", "FL1@mail.com", true);
        user1.setPasswordHash("hash1");
        em.persist(user1);
        User user2 = em.merge(user1);
        Assert.assertTrue(user1.equals(user2));
    }
}
