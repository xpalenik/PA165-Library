package cz.muni.fi.pa165.library.services;

import cz.muni.fi.pa165.library.entities.User;
import cz.muni.fi.pa165.library.repositories.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Arrays;

/**
 * @author Katarína Hermanová
 * UČO 433511
 * Github katHermanova
 */
@RunWith(SpringRunner.class)
public class UserServiceTest {
    private User user;
    private User user2;
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Before
    public void init() {
        userService = new UserService(userRepository);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindByIdNegativeId() {
        userService.findById(-1);
    }

    @Test
    public void testFindByFirstNameNull() {
        Assert.assertTrue(userService.findByFirstName(null).isEmpty());
    }

    @Test
    public void testFindByLastNameNull() {
        Assert.assertTrue(userService.findByLastName(null).isEmpty());
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
        setUser();
        String firstName = user.getFirstName();
        Mockito.when(userRepository.findAll())
                .thenReturn(Arrays.asList(user));
        Assert.assertEquals(Arrays.asList(user), userService.findByFirstName(firstName));
    }

    @Test
    public void testFindMultipleByFirstName() {
        setUser();
        user2 = new User("Kat", "Her", "kHerm2@mail.com", "", true);
        String firstName = user.getFirstName();
        Mockito.when(userRepository.findAll())
                .thenReturn(Arrays.asList(user, user2));
        Assert.assertEquals(Arrays.asList(user, user2), userService.findByFirstName(firstName));
    }

    @Test
    public void testFindByLastName() {
        setUser();
        String lastName = user.getLastName();
        Mockito.when(userRepository.findAll())
                .thenReturn(Arrays.asList(user));
        Assert.assertEquals(Arrays.asList(user), userService.findByLastName(lastName));
    }

    @Test
    public void testFindMultipleByLastName() {
        setUser();
        user2 = new User("K", "Herman", "kHerm2@mail.com", "", true);
        String lastName = user.getLastName();
        Mockito.when(userRepository.findAll())
                .thenReturn(Arrays.asList(user, user2));
        Assert.assertEquals(Arrays.asList(user, user2), userService.findByLastName(lastName));
    }

    @Test
    public void testFindAllLibrarians() {
        setUser();
        user2 = new User("K", "Her", "kHerm2@mail.com", "", true);
        User user3 = new User("Another", "User", "anotUser@mail.com", "", false);
        Mockito.when(userRepository.findAll())
                .thenReturn(Arrays.asList(user, user2, user3));
        Assert.assertEquals(Arrays.asList(user, user2), userService.findAllLibrarians());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddUserNull() {
        userService.addUser(null, "password");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddUserNullPassword() {
        setUser();
        userService.addUser(user, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddUserEmptyPassword() {
        setUser();
        userService.addUser(user, "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddUserWithSameEmail() {
        setUser();
        Mockito.when(userRepository.findAll())
                .thenReturn(Arrays.asList(user));
        User user2 = new User("K", "Her", "kHerm@mail.com", "", true);
        userService.addUser(user2, "password");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddUserWithInvalidUserParameter() {
        setUser();
        user.setLastName(null);
        userService.addUser(user, "password");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteUserNegativeId() {
        userService.deleteUser(-1);
    }

    @Test
    public void testAuthenticate() {
        setUser();
        userService.addUser(user, "ThisIsPassword");
        Assert.assertTrue(userService.authenticate(user, "ThisIsPassword"));
    }

    private void setUser() {
        user = new User("Kat", "Herman", "kHerm@mail.com", "", true);
    }
}