package cz.muni.fi.pa165.library.controllers;

import cz.muni.fi.pa165.library.dto.UserDTO;
import cz.muni.fi.pa165.library.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @author Katarína Hermanová
 * UČO 433511
 * Github katHermanova
 */
@RestController
public class UserController extends AbstractController {

    @Autowired
    private UserFacade userFacade;

    @GetMapping(value = "/user_id/{id}")
    public UserDTO findById(@PathVariable long id) {
        return userFacade.findById(id);
    }

    @GetMapping(value = "/users_firstName/{firstName}")
    public List<UserDTO> findByFirstName(@PathVariable String firstName) {
        return userFacade.findByFirstName(firstName);
    }

    @GetMapping(value = "/users_lastName/{lastName}")
    public List<UserDTO> findByLastName(@PathVariable String lastName) {
        return userFacade.findByLastName(lastName);
    }

    @GetMapping(value = "/user_email/{email}")
    public UserDTO findByEmail(@PathVariable String email) {
        return userFacade.findByEmail(email);
    }

    @GetMapping(value = "/users")
    public List<UserDTO> findAll() {
        return userFacade.findAll();
    }

    @GetMapping(value = "/librarians")
    public List<UserDTO> findAllLibrarians() {
        return userFacade.findAllLibrarians();
    }

    @PostMapping(value = "/users")
    public long addUser(@RequestBody UserDTO user) {
        return userFacade.addUser(user,user.getPasswordHash());
    }

    @DeleteMapping("/delete/user/{id}")
    public void deleteUser(@PathVariable long id) {
        userFacade.deleteUser(id);
    }
}
