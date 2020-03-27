package cz.muni.fi.pa165.library.repositories;

import cz.muni.fi.pa165.library.entities.Book;
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
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/** @author Martin Páleník 359817 */

public class MemberRepositoryTest {

    @RunWith(SpringRunner.class)
    @DataJpaTest
    public class MemberRepositoryIntegrationTest {

        @Autowired
        private TestEntityManager entityManager;

        @Autowired
        private MemberRepository memberRepository;

        @Test
        public void whenFindById_thenReturnMember() {
            // given
            Member martin = new Member();
            martin.setMemberId(1);
            martin.setFirstName("Martin");
            martin.setSurname("R E D A C T E D");
            martin.setLibrarian(false);
            entityManager.persist(martin);
            entityManager.flush();

            // when
            Optional<Member> found = memberRepository.findById(martin.getMemberId());

            // then
            assertThat(found.get().getMemberId())
                    .isEqualTo(martin.getMemberId());
        }

        @Test
        public void findAll() {
            // given
            Member martin = new Member();
            martin.setMemberId(1);
            martin.setFirstName("Martin");
            martin.setSurname("R E D A C T E D");
            martin.setLibrarian(false);
            entityManager.persist(martin);
            entityManager.flush();

            Member librarian = new Member();
            librarian.setMemberId(2);
            librarian.setFirstName("Librarian");
            librarian.setSurname("R E D A C T E D");
            librarian.setLibrarian(true);
            entityManager.persist(librarian);
            entityManager.flush();

            // see https://www.baeldung.com/java-iterable-to-collection
            Iterable<Member> i = memberRepository.findAll();
            List<Member> foundMembers = new ArrayList<Member>();
            i.forEach(foundMembers::add);

            MatcherAssert.assertThat(foundMembers, CoreMatchers.hasItems(martin, librarian));
        }

        @Test
        public void count() {
            // given
            Member martin = new Member();
            martin.setMemberId(1);
            martin.setFirstName("Martin");
            martin.setSurname("R E D A C T E D");
            martin.setLibrarian(false);
            entityManager.persist(martin);
            entityManager.flush();

            Member librarian = new Member();
            librarian.setMemberId(2);
            librarian.setFirstName("Librarian");
            librarian.setSurname("R E D A C T E D");
            librarian.setLibrarian(true);
            entityManager.persist(librarian);
            entityManager.flush();

            assertThat(memberRepository.count())
                    .isEqualTo(Long.valueOf(2));
        }
    }
}