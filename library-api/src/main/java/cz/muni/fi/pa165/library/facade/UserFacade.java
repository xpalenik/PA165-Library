package cz.muni.fi.pa165.library.facade;

import cz.muni.fi.pa165.library.dto.UserAuthenticateDTO;
import cz.muni.fi.pa165.library.dto.UserDTO;
import java.util.List;

/**
 * @author Katarína Hermanová
 * UČO 433511
 * Github katHermanova
 */
public interface UserFacade {

    UserDTO findById(long id);
    List<UserDTO> findByFirstName(String firstName);
    List<UserDTO> findByLastName(String lastName);
    List<UserDTO> findByEmail(String email);
    List<UserDTO> findAll();
    void addUser(UserDTO user);
    void deleteUser(long id);
    void deleteAllUsers();
    long count();
    boolean authenticate(UserAuthenticateDTO u);
    boolean isLibrarian(UserDTO user);
}
