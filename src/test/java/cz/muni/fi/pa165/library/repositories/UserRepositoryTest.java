package cz.muni.fi.pa165.library.repositories;

import cz.muni.fi.pa165.library.entities.User;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

/** @author Martin Páleník 359817 */

@RunWith(SpringRunner.class)
@DataJpaTest
@Ignore
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findById() {
        // given
        User martin = new User();
        martin.setFirstName("Martin");
        martin.setLastName("REDACTED");
        entityManager.persist(martin);
        entityManager.flush();

        // when
        Optional<User> found = userRepository.findById(martin.getId());

        // then
        assertThat(found.get().getId())
                .isEqualTo(martin.getId());
        assertThat(found.get().getFirstName())
                .isEqualTo(martin.getFirstName());
        assertThat(found.get().getLastName())
                .isEqualTo(martin.getLastName());
    }

    @Test
    public void findAll() {
        // given
        User martin = new User();
        martin.setFirstName("Martin");
        martin.setLastName("REDACTED");
        entityManager.persist(martin);
        entityManager.flush();

        User librarian = new User();
        librarian.setFirstName("Librarian");
        librarian.setLastName("REDACTED");
        entityManager.persist(librarian);
        entityManager.flush();

        // see https://www.baeldung.com/java-iterable-to-collection
        Iterable<User> i = userRepository.findAll();
        List<User> foundUsers = new ArrayList<>();
        i.forEach(foundUsers::add);

        MatcherAssert.assertThat(foundUsers, containsInAnyOrder(martin, librarian));
    }

    @Test
    public void count() {
        // given
        User martin = new User();
        entityManager.persist(martin);
        entityManager.flush();

        User librarian = new User();
        entityManager.persist(librarian);
        entityManager.flush();

        assertThat(userRepository.count())
                .isEqualTo(Long.valueOf(2));
    }

    @Test
    public void deleteById() {
        // given
        User martin = new User();
        entityManager.persist(martin);
        entityManager.flush();

        User librarian = new User();
        entityManager.persist(librarian);
        entityManager.flush();

        userRepository.deleteById(librarian.getId());

        assertThat(userRepository.count())
                .isEqualTo(Long.valueOf(1));
    }
}
