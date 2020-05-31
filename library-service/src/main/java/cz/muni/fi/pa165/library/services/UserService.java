package cz.muni.fi.pa165.library.services;

import cz.muni.fi.pa165.library.entities.User;
import cz.muni.fi.pa165.library.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * service layer class for User
 * any business logic should be implemented here
 *
 * @author Katarína Hermanová
 * UČO 433511
 * Github katHermanova
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    /**
     * method is looking for specific User by id
     *
     * @param id of user we are looking for
     * @return User
     * @throws IllegalArgumentException if id less than 0
     */
    public User findById(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("ID can not be less than 0.");
        }
        return userRepository.findById(id).get();
    }

    /**
     * method is looking for users by first name
     *
     * @param firstName
     * @return list of users or empty list if non matches
     * @throws IllegalArgumentException if firstName is illegal argument
     */
    public List<User> findByFirstName(String firstName) {
        List<User> foundUsers = new ArrayList<>();
        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException("FirstName is empty or null.");
        }

        for (User user : findAll()) {
            if (user.getFirstName().equals(firstName)) {
                foundUsers.add(user);
            }
        }
        return foundUsers;
    }

    /**
     * method is looking for users by lastName
     *
     * @param lastName
     * @return list of users or empty list if non matches
     * @throws IllegalArgumentException if lastName is illegal argument
     */
    public List<User> findByLastName(String lastName) {
        List<User> foundUsers = new ArrayList<>();
        if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException("LastName is empty or null.");
        }

        for (User user : findAll()) {
            if (user.getLastName().equals(lastName)) {
                foundUsers.add(user);
            }
        }
        return foundUsers;
    }

    /**
     * method is looking for users by email
     *
     * @param email
     * @return user or null if non matches
     * @throws IllegalArgumentException if email is illegal argument
     */
    public User findByEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email is empty or null.");
        }

        for (User user : findAll()) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    /**
     * @return list of all users
     */
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     *
     * @return list of all librarians
     */
    public List<User> findAllLibrarians() {
        List<User> librarians = new ArrayList<>();
        for (User user: findAll()) {
            if (user.isLibrarian()) {
                librarians.add(user);
            }
        }
        return librarians;
    }

    /**
     * method adds user
     *
     * @param user
     * @return id of user
     * @throws IllegalArgumentException if user is null or has illegal attributes
     */
    public long addUser(User user, String password) {
        if (user == null) {
            throw new IllegalArgumentException("Can not add non-existing user.");
        }
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password is empty or null.");
        }
        if (user.getFirstName() == null || user.getLastName() == null
                || user.getEmail() == null) {
            throw new IllegalArgumentException("User we adding has null attribute.");
        }
        for (User u : findAll()) {
            if (u.getEmail().equals(user.getEmail())) {
                throw new IllegalArgumentException("Email is already used by another user.");
            }
        }
        user.setPasswordHash(new BCryptPasswordEncoder().encode(password));
        userRepository.save(user);
        LOGGER.info("User was added.");
        return user.getId();
    }

    /**
     * method deletes user by id
     *
     * @param id of user
     * @throws IllegalArgumentException if ID is less than 0
     */
    public void deleteUser(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("Id can not be less than 0.");
        }
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            LOGGER.info("User deleted.");
        } else {
            LOGGER.warn("Trying to delete non-existing user.");
        }
    }
}
