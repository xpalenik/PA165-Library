package cz.muni.fi.pa165.library.services;

import cz.muni.fi.pa165.library.entities.Member;
import cz.muni.fi.pa165.library.repositories.MemberRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

/** @author Martin Páleník 359817
 *  based on https://github.com/katHermanova/PA165-Library/blob/develop/src/test/java/cz/muni/fi/pa165/library/services/BookServiceTest.java
 *  based on https://www.baeldung.com/spring-boot-testing
 *  based on https://www.baeldung.com/introduction-to-assertj
 */

@RunWith(SpringRunner.class)
public class MemberServiceTest {

    private Member martin, jurgen, librarian, anotherLibrarian;
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    @Before
    public void init() {
        memberService = new MemberService(memberRepository);
    }

    // Test null values as arguments

    @Test
    public void testFindMemberByFirstNameNull() {
        Assert.assertTrue(memberService.findByFirstName(null).isEmpty());
    }

    @Test
    public void testFindMemberBySurnameNull() {
        Assert.assertTrue(memberService.findBySurname(null).isEmpty());
    }

    // Test one item found

    @Test
    public void testFindMemberByFirstName() {
        martin = new Member();
        martin.setFirstName("Martin");

        jurgen = new Member();
        jurgen.setFirstName("Jürgen");

        Mockito.when(memberRepository.findAll())
                .thenReturn(Arrays.asList(martin, jurgen));

        Assert.assertEquals(
                Arrays.asList(martin),
                memberService.findByFirstName(martin.getFirstName())
        );
    }

    @Test
    public void testFindMemberBySurname() {
        martin = new Member();
        martin.setSurname("REDACTED");

        jurgen = new Member();
        jurgen.setSurname("Kohn");

        Mockito.when(memberRepository.findAll())
                .thenReturn(Arrays.asList(martin, jurgen));

        Assert.assertEquals(
                Arrays.asList(martin),
                memberService.findBySurname(martin.getSurname())
        );
    }

    @Test
    public void testFindFullName() {
        martin = new Member();
        martin.setFirstName("Martin");
        martin.setSurname("REDACTED");


        jurgen = new Member();
        jurgen.setFirstName("Jürgen");
        jurgen.setSurname("Kohn");

        Mockito.when(memberRepository.findAll())
                .thenReturn(Arrays.asList(martin, jurgen));

        // ???
        // should be available in a service
    }


    @Test
    public void testFindMultipleSurnames() {
        martin = new Member();
        martin.setFirstName("Martin");
        martin.setSurname("REDACTED");


        jurgen = new Member();
        jurgen.setFirstName("Jürgen");
        jurgen.setSurname("REDACTED");

        Mockito.when(memberRepository.findAll())
                .thenReturn(Arrays.asList(martin, jurgen));

        Assert.assertEquals(
                Arrays.asList(martin, jurgen),
                memberService.findBySurname(martin.getSurname())
        );
    }

    @Test
    public void testFindMultipleLibrarians() {
        martin = new Member();
        martin.setLibrarian(false);

        jurgen = new Member();
        jurgen.setLibrarian(false);

        librarian = new Member();
        librarian.setLibrarian(true);

        anotherLibrarian = new Member();
        anotherLibrarian.setLibrarian(true);

        Mockito.when(memberRepository.findAll())
                .thenReturn(Arrays.asList(martin, jurgen, librarian, anotherLibrarian));

        Assert.assertEquals(
                Arrays.asList(librarian, anotherLibrarian),
                memberService.findLibrarians()
        );
    }

    @Test
    public void testCount() {

        Mockito.when(memberRepository.count())
                .thenReturn((long)4);

        Assert.assertEquals(
                4,
                memberService.count()
        );
    }

    @Test
    public void testCountLibrarians() {

        martin = new Member();
        martin.setLibrarian(false);

        jurgen = new Member();
        jurgen.setLibrarian(false);

        librarian = new Member();
        librarian.setLibrarian(true);

        anotherLibrarian = new Member();
        anotherLibrarian.setLibrarian(true);

        Mockito.when(memberRepository.findAll())
                .thenReturn(Arrays.asList(martin, jurgen, librarian, anotherLibrarian));


        Assert.assertEquals(
                2,
                memberService.librariansCount()
        );
    }

    @Test
    public void testFindAll() {

        Mockito.when(memberRepository.findAll())
                .thenReturn(Arrays.asList(martin, jurgen, librarian, anotherLibrarian));

        Assert.assertEquals(
                Arrays.asList(martin, jurgen, librarian, anotherLibrarian),
                memberService.findAll()
        );
    }

}