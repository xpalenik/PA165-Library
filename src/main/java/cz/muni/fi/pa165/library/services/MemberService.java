package cz.muni.fi.pa165.library.services;

import cz.muni.fi.pa165.library.entities.Member;
import cz.muni.fi.pa165.library.repositories.MemberRepository;
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
public class MemberService {

    private MemberRepository memberRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(MemberService.class);

    /**
     * class constructor
     *
     * @param memberRepository MemberDAO
     */
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * method is looking for specific Member with given ID
     *
     * @param id is ID of member we are looking for
     * @return Member object with given ID
     * @throws IllegalArgumentException if ID less than 0
     */
    public Member findById(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("ID can not be less than 0.");
        }
        return memberRepository.findById(id).get();
    }

    /**
     * method is looking for members with first name same as given one
     *
     * @param firstName is first name of members we are looking for
     * @return list of Members with given first name or empty list if non matches or first name is illegal argument
     */
    public List<Member> findByFirstName(String firstName) {
        List<Member> foundMembers = new ArrayList<>();
        if (firstName == null || firstName.isEmpty()) {
            return foundMembers;
        }

        for (Member member : memberRepository.findAll()) {
            if (member.getFirstName().equals(firstName)) {
                foundMembers.add(member);
            }
        }
        return foundMembers;
    }

    /**
     * method is looking for members with surname same as given one
     *
     * @param surname is surname of members we are looking for
     * @return list of Members with given surname or empty list if non matches or surname is illegal argument
     */
    public List<Member> findBySurname(String surname) {
        List<Member> foundMembers = new ArrayList<>();
        if (surname == null || surname.isEmpty()) {
            return foundMembers;
        }

        for (Member member : memberRepository.findAll()) {
            if (member.getSurname().equals(surname)) {
                foundMembers.add(member);
            }
        }
        return foundMembers;
    }

    /**
     * method returning all members
     *
     * @return list of all Members
     */
    public List<Member> findAll() {
        return (List<Member>) memberRepository.findAll();
    }

    /**
     * method adds or updates member
     *
     * @param member member we want to add or update
     * @throws IllegalArgumentException if member is null
     */
    public void addMember(Member member) {
        if (member == null) {
            throw new IllegalArgumentException("Can not add non-existing member.");
        }
        memberRepository.save(member);
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
        if (memberRepository.existsById(id)) {
            memberRepository.deleteById(id);
            LOGGER.info("Member deleted.");
        } else {
            LOGGER.warn("Trying to delete non-existing member.");
        }
    }

    /**
     * method deletes all members
     */
    public void deleteAllMembers() {
        memberRepository.deleteAll();
        LOGGER.info("All members was deleted.");
    }

    /**
     * method returns count of all members
     *
     * @return count of all members
     */
    public long count() {
        return memberRepository.count();
    }
}
