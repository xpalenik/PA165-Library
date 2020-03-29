package cz.muni.fi.pa165.library.repositories;

import cz.muni.fi.pa165.library.entities.Member;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/** @author Martin Páleník 359817 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class MemberRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void findById() {
        // given
        Member martin = new Member();
        martin.setFirstName("Martin");
        martin.setSurname("REDACTED");
        martin.setLibrarian(false);
        entityManager.persist(martin);
        entityManager.flush();

        // when
        Optional<Member> found = memberRepository.findById(martin.getId());

        // then
        assertThat(found.get().getId())
                .isEqualTo(martin.getId());
        assertThat(found.get().getFirstName())
                .isEqualTo(martin.getFirstName());
        assertThat(found.get().getSurname())
                .isEqualTo(martin.getSurname());
    }

    @Test
    public void findAll() {
        // given
        Member martin = new Member();
        martin.setFirstName("Martin");
        martin.setSurname("REDACTED");
        martin.setLibrarian(false);
        entityManager.persist(martin);
        entityManager.flush();

        Member librarian = new Member();
        librarian.setFirstName("Librarian");
        librarian.setSurname("REDACTED");
        librarian.setLibrarian(true);
        entityManager.persist(librarian);
        entityManager.flush();

        // see https://www.baeldung.com/java-iterable-to-collection
        Iterable<Member> i = memberRepository.findAll();
        List<Member> foundMembers = new ArrayList<>();
        i.forEach(foundMembers::add);

        MatcherAssert.assertThat(foundMembers, CoreMatchers.hasItems(martin, librarian));
    }

    @Test
    public void count() {
        // given
        Member martin = new Member();
        entityManager.persist(martin);
        entityManager.flush();

        Member librarian = new Member();
        entityManager.persist(librarian);
        entityManager.flush();

        assertThat(memberRepository.count())
                .isEqualTo(Long.valueOf(2));
    }

    @Test
    public void deleteById() {
        // given
        Member martin = new Member();
        entityManager.persist(martin);
        entityManager.flush();

        Member librarian = new Member();
        entityManager.persist(librarian);
        entityManager.flush();

        memberRepository.deleteById(librarian.getId());

        assertThat(memberRepository.count())
                .isEqualTo(Long.valueOf(1));
    }
}
