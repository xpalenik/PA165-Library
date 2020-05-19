package cz.muni.fi.pa165.library.services;

import cz.muni.fi.pa165.library.entities.User;
import cz.muni.fi.pa165.library.repositories.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
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

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

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

        Mockito.when(userRepository.findAll()).thenReturn(Arrays.asList(user));
        userService.addUser(user2, "password");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddUserWithInvalidUserParameter() {
        User user = new User("Kat", null, "kHerm@mail.com", true);

        userService.addUser(user, "password");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindByFirstNameNull() {
        userService.findByFirstName(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindByFirstNameEmptyString() {
        userService.findByFirstName("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindByLastNameNull() {
        userService.findByLastName(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindByLastNameEmptyString() {
        userService.findByLastName("");
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
        String firstName = user.getFirstName();

        Mockito.when(userRepository.findAll()).thenReturn(Arrays.asList(user));

        Assert.assertEquals(Arrays.asList(user), userService.findByFirstName(firstName));
    }

    @Test
    public void testFindMultipleByFirstName() {
        User user = new User("Kat", "Herman", "kHerm@mail.com", true);
        User user2 = new User("Kat", "Her", "kHerm2@mail.com", true);
        String firstName = user.getFirstName();

        Mockito.when(userRepository.findAll()).thenReturn(Arrays.asList(user, user2));

        Assert.assertEquals(Arrays.asList(user, user2), userService.findByFirstName(firstName));
    }

    @Test
    public void testFindByLastName() {
        User user = new User("Kat", "Herman", "kHerm@mail.com", true);
        String lastName = user.getLastName();

        Mockito.when(userRepository.findAll()).thenReturn(Arrays.asList(user));

        Assert.assertEquals(Arrays.asList(user), userService.findByLastName(lastName));
    }

    @Test
    public void testFindMultipleByLastName() {
        User user = new User("Kat", "Herman", "kHerm@mail.com", true);
        User user2 = new User("K", "Herman", "kHerm2@mail.com", true);
        String lastName = user.getLastName();

        Mockito.when(userRepository.findAll()).thenReturn(Arrays.asList(user, user2));

        Assert.assertEquals(Arrays.asList(user, user2), userService.findByLastName(lastName));
    }

    @Test
    public void testFindAllLibrarians() {
        User user = new User("Kat", "Herman", "kHerm@mail.com", true);
        User user2 = new User("K", "Her", "kHerm2@mail.com", true);
        User user3 = new User("Another", "User", "anotUser@mail.com", false);

        Mockito.when(userRepository.findAll()).thenReturn(Arrays.asList(user, user2, user3));

        Assert.assertEquals(Arrays.asList(user, user2), userService.findAllLibrarians());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteUserNegativeId() {
        userService.deleteUser(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteUser() {
        userService.deleteUser(-1);
    }
}