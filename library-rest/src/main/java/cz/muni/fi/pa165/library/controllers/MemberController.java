package cz.muni.fi.pa165.library.controllers;

import cz.muni.fi.pa165.library.entities.User;
import cz.muni.fi.pa165.library.services.UserService;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Katarína Hermanová
 * UČO 433511
 * Github katHermanova
 */
@RestController
@Transactional
public class MemberController extends AbstractController {

    private UserService userService;

    public MemberController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/members", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> findAllMembers() {
        return userService.findAll();
    }
}
