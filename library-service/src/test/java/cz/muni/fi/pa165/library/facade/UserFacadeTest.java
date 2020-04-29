package cz.muni.fi.pa165.library.facade;

import cz.muni.fi.pa165.library.dto.UserAuthenticateDTO;
import cz.muni.fi.pa165.library.dto.UserDTO;
import cz.muni.fi.pa165.library.entities.User;
import cz.muni.fi.pa165.library.services.MappingService;
import cz.muni.fi.pa165.library.services.UserService;
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
public class UserFacadeTest {
    private User user;
    private User user2;
    private UserDTO userDTO;
    private UserDTO userDTO2;

    @InjectMocks
    private UserFacadeImpl userFacade;

    @Mock
    private UserService userService;

    @Mock
    private MappingService mappingService;

    @Test
    public void testFindById() {
        setUser();
        Mockito.when(userService.findById(user.getId())).thenReturn(user);
        Mockito.when(mappingService.mapTo(user, UserDTO.class)).thenReturn(userDTO);
        Assert.assertEquals(userDTO, userFacade.findById(user.getId()));
    }

    @Test
    public void testFindByFirstName() {
        setUser();
        Mockito.when(mappingService.mapTo(userService.findByFirstName(user.getFirstName()), UserDTO.class)).thenReturn(Arrays.asList(userDTO));
        Assert.assertEquals(Arrays.asList(userDTO), userFacade.findByFirstName(user.getFirstName()));
    }

    @Test
    public void testFindByLastName() {
        setUser();
        Mockito.when(mappingService.mapTo(userService.findByLastName(user.getLastName()), UserDTO.class)).thenReturn(Arrays.asList(userDTO));
        Assert.assertEquals(Arrays.asList(userDTO), userFacade.findByLastName(user.getLastName()));
    }

    @Test
    public void testFindByEmail() {
        setUser();
        Mockito.when(mappingService.mapTo(userService.findByEmail(user.getEmail()), UserDTO.class)).thenReturn(userDTO);
        Assert.assertEquals(userDTO, userFacade.findByEmail(user.getEmail()));
    }

    @Test
    public void testFindAll() {
        setTwoUsers();
        Mockito.when(mappingService.mapTo(userService.findAll(), UserDTO.class)).thenReturn(Arrays.asList(userDTO, userDTO2));
        Assert.assertEquals(Arrays.asList(userDTO, userDTO2), userFacade.findAll());
    }

    @Test
    public void testFindAllLibrarians() {
        setTwoUsers();
        Mockito.when(mappingService.mapTo(userService.findAllLibrarians(), UserDTO.class)).thenReturn(Arrays.asList(userDTO, userDTO2));
        Assert.assertEquals(Arrays.asList(userDTO, userDTO2), userFacade.findAllLibrarians());
    }

    @Test
    public void testAddUser() {
        setUser();

        Mockito.when(userService.addUser(mappingService.mapTo(userDTO, User.class), "password")).thenReturn(user.getId());
        Assert.assertEquals(user.getId(), userFacade.addUser(userDTO, "password"));
    }

    @Test
    public void testAuthenticate() {
        setUser();
        UserAuthenticateDTO userAuthenticateDTO = new UserAuthenticateDTO(userDTO.getId(), "password");
        Mockito.when(userService.authenticate(userService.findById(userAuthenticateDTO.getUserId()), userAuthenticateDTO.getPassword())).thenReturn(true);
        Assert.assertTrue(userFacade.authenticate(userAuthenticateDTO));
    }

    private void setUser() {
        user = new User("Kat", "Herman", "kHerm@mail.com", true);
        user.setId(123);
        user.setPasswordHash("passwordHash");
        userDTO = new UserDTO(user.getId(), "Kat", "Herman", "kHerm@mail.com", user.getPasswordHash(), true);
    }

    private void setTwoUsers() {
        setUser();
        user2 = new User("K", "Her", "kHerm2@mail.com", true);
        user2.setId(456);
        user2.setPasswordHash("passwordHash2");
        userDTO2 = new UserDTO(user2.getId(), "K", "Her", "kHerm2@mail.com", user2.getPasswordHash(), true);
    }
}
