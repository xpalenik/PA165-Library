package cz.muni.fi.pa165.library.controllers;

import cz.muni.fi.pa165.library.entities.Member;
import cz.muni.fi.pa165.library.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @author Katarína Hermanová
 * UČO 433511
 * Github katHermanova
 */
@RestController
@Transactional
public class MemberController extends AbstractController{

    @Autowired
    private MemberService memberService;

    @GetMapping(value = "/members", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Member> findAllMembers() {
        return memberService.findAll();
    }
}
