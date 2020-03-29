package cz.muni.fi.pa165.library.services;

import cz.muni.fi.pa165.library.entities.User;
import cz.muni.fi.pa165.library.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private UserRepository userRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    /**
     * class constructor
     *
     * @param userRepository UserDAO
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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
     * @return list of users or empty list if non matches or first name is illegal argument
     */
    public List<User> findByFirstName(String firstName) {
        List<User> foundUsers = new ArrayList<>();
        if (firstName == null || firstName.isEmpty()) {
            return foundUsers;
        }

        for (User user : userRepository.findAll()) {
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
     * @return list of users or empty list if non matches or last name is illegal argument
     */
    public List<User> findByLastName(String lastName) {
        List<User> foundUsers = new ArrayList<>();
        if (lastName == null || lastName.isEmpty()) {
            return foundUsers;
        }

        for (User user : userRepository.findAll()) {
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
     * @return list of users or empty list if non matches or last name is illegal argument
     */
    public List<User> findByEmail(String email) {
        List<User> foundUsers = new ArrayList<>();
        if (email == null || email.isEmpty()) {
            return foundUsers;
        }

        for (User user : userRepository.findAll()) {
            if (user.getEmail().equals(email)) {
                foundUsers.add(user);
            }
        }
        return foundUsers;
    }

    /**
     *
     *
     * @return list of all users
     */
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    /**
     * method adds or updates user
     *
     * @param user
     * @throws IllegalArgumentException if user is null or has illegal attributes
     */
    public void addUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("Can not add non-existing user.");
        }
        if (user.getFirstName() == null || user.getLastName() == null
                || user.getEmail() == null || user.getPassword() == null) {
            throw new IllegalArgumentException("User we adding has illegal null attribute.");
        }
        if (user.getPassword().length() > 60) {
            throw new IllegalArgumentException("User password is too long.");
        }
        if (user.getRoles().isEmpty()) {
            throw new IllegalArgumentException("User must have at least one role.");
        }
        userRepository.save(user);
        LOGGER.info("User was added.");
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

    /**
     * method deletes all users
     */
    public void deleteAllUsers() {
        userRepository.deleteAll();
        LOGGER.info("All users was deleted.");
    }

    /**
     * @return count of all users
     */
    public long count() {
        return userRepository.count();
    }
}
