package cz.muni.fi.pa165.library.facade;

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
    UserDTO findByEmail(String email);
    List<UserDTO> findAll();
    List<UserDTO> findAllLibrarians();
    long addUser(UserDTO user, String password);
    void deleteUser(long id);
}
