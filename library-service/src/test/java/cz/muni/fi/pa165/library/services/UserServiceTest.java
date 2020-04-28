package cz.muni.fi.pa165.library.services;

import cz.muni.fi.pa165.library.entities.User;
import cz.muni.fi.pa165.library.repositories.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Arrays;

/**
 * @author Katarína Hermanová
 * UČO 433511
 * Github katHermanova
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test(expected = IllegalArgumentException.class)
    public void testFindByIdNegativeId() {
        userService.findById(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddUserNull() {
        userService.addUser(null, "password");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddUserNullPassword() {
        User user = new User("Kat", "Herman", "kHerm@mail.com", true);
        userService.addUser(user, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddUserEmptyPassword() {
        User user = new User("Kat", "Herman", "kHerm@mail.com", true);
        userService.addUser(user, "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddUserWithSameEmail() {
        User user = new User("Kat", "Herman", "kHerm@mail.com", true);
        User user2 = new User("K", "Her", "kHerm@mail.com", true);
        userService.addUser(user, "password");
        userService.addUser(user2, "password");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddUserWithInvalidUserParameter() {
        User user = new User("Kat", null, "kHerm@mail.com", true);
        userService.addUser(user, "password");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindByFirstNameNull() {
        Assert.assertTrue(userService.findByFirstName(null).isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindByFirstNameEmptyString() {
        Assert.assertTrue(userService.findByFirstName("").isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindByLastNameNull() {
        Assert.assertTrue(userService.findByLastName(null).isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindByLastNameEmptyString() {
        Assert.assertTrue(userService.findByLastName("").isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindByEmailNull() {
        userService.findByEmail(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindByEmailEmptyString() {
        userService.findByEmail("");
    }

    @Test
    public void testFindByFirstName() {
        User user = new User("Kat", "Herman", "kHerm@mail.com", true);
        userService.addUser(user, "password");
        String firstName = user.getFirstName();
        Assert.assertEquals(Arrays.asList(user), userService.findByFirstName(firstName));
    }

    @Test
    public void testFindMultipleByFirstName() {
        User user = new User("Kat", "Herman", "kHerm@mail.com", true);
        User user2 = new User("Kat", "Her", "kHerm2@mail.com", true);
        userService.addUser(user, "password");
        userService.addUser(user2, "password2");
        String firstName = user.getFirstName();
        Assert.assertEquals(Arrays.asList(user, user2), userService.findByFirstName(firstName));
    }

    @Test
    public void testFindByLastName() {
        User user = new User("Kat", "Herman", "kHerm@mail.com", true);
        userService.addUser(user, "password");
        String lastName = user.getLastName();
        Assert.assertEquals(Arrays.asList(user), userService.findByLastName(lastName));
    }

    @Test
    public void testFindMultipleByLastName() {
        User user = new User("Kat", "Herman", "kHerm@mail.com", true);
        User user2 = new User("K", "Herman", "kHerm2@mail.com", true);
        userService.addUser(user, "password");
        userService.addUser(user2, "password2");
        String lastName = user.getLastName();
        Assert.assertEquals(Arrays.asList(user, user2), userService.findByLastName(lastName));
    }

    @Test
    public void testFindAllLibrarians() {
        User user = new User("Kat", "Herman", "kHerm@mail.com", true);
        User user2 = new User("K", "Her", "kHerm2@mail.com", true);
        User user3 = new User("Another", "User", "anotUser@mail.com", false);
        userService.addUser(user, "password");
        userService.addUser(user2, "password2");
        userService.addUser(user3, "password3");
        Assert.assertEquals(Arrays.asList(user, user2), userService.findAllLibrarians());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteUserNegativeId() {
        userService.deleteUser(-1);
    }

    @Test
    public void testAuthenticate() {
        User user = new User("Kat", "Herman", "kHerm@mail.com", true);
        userService.addUser(user, "password");
        Assert.assertTrue(userService.authenticate(user, "password"));
    }
}