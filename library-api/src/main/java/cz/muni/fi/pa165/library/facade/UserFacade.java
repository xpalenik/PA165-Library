package cz.muni.fi.pa165.library.facade;

import cz.muni.fi.pa165.library.dto.UserDTO;
import java.util.List;

/**
 * @author Katarína Hermanová
 * UČO 433511
 * Github katHermanova
 *
 * Front-facing interface for book methods
 */
public interface UserFacade {
    /**
     *
     * @param id
     * @return user having certain id
     */
    UserDTO findById(long id);

    /**
     * method gets all users having certain first name
     *
     * @param firstName
     * @return list of users having certain first name
     */
    List<UserDTO> findByFirstName(String firstName);

    /**
     * method gets all users having certain last name
     *
     * @param lastName
     * @return list of users having certain last name
     */
    List<UserDTO> findByLastName(String lastName);

    /**
     *
     * @param email
     * @return user having certain email
     */
    UserDTO findByEmail(String email);

    /**
     *
     * @return list of all existing users
     */
    List<UserDTO> findAll();

    /**
     *
     * @return all existing users, who are librarian
     */
    List<UserDTO> findAllLibrarians();

    /**
     * method create new user and if valid add it to DB along with encrypted password
     *
     * @param user to add
     * @param password associated with user
     * @return id of created user
     */
    long addUser(UserDTO user, String password);

    /**
     * if user exists method remove it
     *
     * @param id of the user we wanna delete
     */
    void deleteUser(long id);
}
