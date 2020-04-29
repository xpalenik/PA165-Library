package cz.muni.fi.pa165.library.facade;

import cz.muni.fi.pa165.library.dto.UserAuthenticateDTO;
import cz.muni.fi.pa165.library.dto.UserDTO;
import cz.muni.fi.pa165.library.entities.User;
import cz.muni.fi.pa165.library.services.MappingService;
import cz.muni.fi.pa165.library.services.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * @author Katarína Hermanová
 * UČO 433511
 * Github katHermanova
 */
@Service
@Transactional
public class UserFacadeImpl implements UserFacade {
    private UserService userService;
    private MappingService mappingService;

    public UserFacadeImpl(MappingService mappingService, UserService userService) {
        this.mappingService = mappingService;
        this.userService = userService;
    }

    @Override
    public UserDTO findById(long id) {
        User user = userService.findById(id);
        if (user != null) {
            return mappingService.mapTo(user, UserDTO.class);
        }
        return null;
    }

    @Override
    public List<UserDTO> findByFirstName(String firstName) {
        return mappingService.mapTo(userService.findByFirstName(firstName), UserDTO.class);
    }

    @Override
    public List<UserDTO> findByLastName(String lastName) {
        return mappingService.mapTo(userService.findByLastName(lastName), UserDTO.class);
    }

    @Override
    public UserDTO findByEmail(String email) {
        return mappingService.mapTo(userService.findByEmail(email), UserDTO.class);
    }

    @Override
    public List<UserDTO> findAll() {
        return mappingService.mapTo(userService.findAll(), UserDTO.class);
    }

    @Override
    public List<UserDTO> findAllLibrarians() {
        return mappingService.mapTo(userService.findAllLibrarians(), UserDTO.class);
    }

    @Override
    public long addUser(UserDTO user,  String password) {
        return userService.addUser(mappingService.mapTo(user, User.class), password);
    }

    @Override
    public void deleteUser(long id) {
        userService.deleteUser(id);
    }

    @Override
    public boolean authenticate(UserAuthenticateDTO u) {
        return userService.authenticate(userService.findById(u.getUserId()), u.getPassword());
    }
}
