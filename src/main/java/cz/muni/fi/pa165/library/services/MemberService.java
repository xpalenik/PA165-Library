package cz.muni.fi.pa165.library.services;

import cz.muni.fi.pa165.library.entities.Member;
import cz.muni.fi.pa165.library.repositories.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Katarína Hermanová
 * UČO 433511
 * Github katHermanova
 */
@Service
public class MemberService {

    private MemberRepository memberRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(MemberService.class);

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member findById(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("ID can not be less than 0.");
        }
        return memberRepository.findById(id).get();
    }

    public List<Member> findByFirstName(String firstName) {
        List<Member> foundMembers = new ArrayList<>();
        if (firstName == null || firstName.isEmpty()) {
            return foundMembers;
        }

        for (Member member: memberRepository.findAll()) {
            if (member.getFirstName().equals(firstName)) {
                foundMembers.add(member);
            }
        }
        return foundMembers;
    }

    public List<Member> findBySurname(String surname) {
        List<Member> foundMembers = new ArrayList<>();
        if (surname == null || surname.isEmpty()) {
            return foundMembers;
        }

        for (Member member: memberRepository.findAll()) {
            if (member.getSurname().equals(surname)) {
                foundMembers.add(member);
            }
        }
        return foundMembers;
    }

    public List<Member> findLibrarians() {
        return getMembersByLibraryRole(true);
    }

    public List<Member> findNonLibrarians() {
        return getMembersByLibraryRole(false);
    }

    public List<Member> findAll() {
        return (List<Member>)memberRepository.findAll();
    }

    public void addMember(Member member) {
        if (member == null) {
            throw new IllegalArgumentException("Can not add non-existing member.");
        }
        memberRepository.save(member);
        LOGGER.info("Member was added.");
    }

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

    public void deleteAllMembers() {
        memberRepository.deleteAll();
        LOGGER.info("All members was deleted.");
    }

    public long count() {
        return memberRepository.count();
    }

    private List<Member> getMembersByLibraryRole(boolean lookingLibrarians) {
        List<Member> foundMembers = new ArrayList<>();

        for (Member member: memberRepository.findAll()) {
            if (lookingLibrarians && member.isLibrarian()) {
                foundMembers.add(member);
            }
            if (!lookingLibrarians && !member.isLibrarian()) {
                foundMembers.add(member);
            }
        }
        return foundMembers;
    }
}
