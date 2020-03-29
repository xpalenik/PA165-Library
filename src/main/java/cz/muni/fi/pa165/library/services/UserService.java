package cz.muni.fi.pa165.library.services;

import cz.muni.fi.pa165.library.entities.User;
import cz.muni.fi.pa165.library.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * service layer class for Member methods
 * any business logic should implemented be here
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
     * @param userRepository MemberDAO
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * method is looking for specific Member with given ID
     *
     * @param id is ID of member we are looking for
     * @return Member object with given ID
     * @throws IllegalArgumentException if ID less than 0
     */
    public User findById(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("ID can not be less than 0.");
        }
        return userRepository.findById(id).get();
    }

    /**
     * method is looking for members with first name same as given one
     *
     * @param firstName is first name of members we are looking for
     * @return list of Members with given first name or empty list if non matches or first name is illegal argument
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
     * method is looking for members with surname same as given one
     *
     * @param surname is surname of members we are looking for
     * @return list of Members with given surname or empty list if non matches or surname is illegal argument
     */
    public List<User> findBySurname(String surname) {
        List<User> foundUsers = new ArrayList<>();
        if (surname == null || surname.isEmpty()) {
            return foundUsers;
        }

        for (User user : userRepository.findAll()) {
            if (user.getLastName().equals(surname)) {
                foundUsers.add(user);
            }
        }
        return foundUsers;
    }

    /**
     * method returning all members
     *
     * @return list of all Members
     */
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    /**
     * method adds or updates member
     *
     * @param user member we want to add or update
     * @throws IllegalArgumentException if member is null
     */
    public void addMember(User user) {
        if (user == null) {
            throw new IllegalArgumentException("Can not add non-existing member.");
        }
        userRepository.save(user);
        LOGGER.info("Member was added.");
    }

    /**
     * method deletes specific Member with given ID
     *
     * @param id is ID of member we are looking for
     * @throws IllegalArgumentException if ID is less than 0
     */
    public void deleteMember(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("ID can not be less than 0.");
        }
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            LOGGER.info("Member deleted.");
        } else {
            LOGGER.warn("Trying to delete non-existing member.");
        }
    }

    /**
     * method deletes all members
     */
    public void deleteAllMembers() {
        userRepository.deleteAll();
        LOGGER.info("All members was deleted.");
    }

    /**
     * method returns count of all members
     *
     * @return count of all members
     */
    public long count() {
        return userRepository.count();
    }
}
