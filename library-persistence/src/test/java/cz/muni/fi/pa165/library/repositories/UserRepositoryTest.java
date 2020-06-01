package cz.muni.fi.pa165.library.repositories;

import cz.muni.fi.pa165.library.entities.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.junit4.SpringRunner;
import javax.persistence.PersistenceException;
import java.util.Optional;
import static com.github.npathai.hamcrestopt.OptionalMatchers.isPresentAndIs;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Martin Páleník 359817
 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findById() {
        // given
        User martin = createTestUserMartin();
        entityManager.persist(martin);

        // then
        assertThat(userRepository.findById(martin.getId()), isPresentAndIs(martin));
    }

    @Test
    public void findAll() {
        // given
        User martin = createTestUserMartin();
        entityManager.persist(martin);

        User librarian = createTestUserBoris();
        entityManager.persist(librarian);

        assertThat(userRepository.findAll(), containsInAnyOrder(martin, librarian));
    }

    @Test
    public void deleteById() {
        // given
        User martin = createTestUserMartin();
        entityManager.persist(martin);

        User librarian = createTestUserBoris();
        entityManager.persist(librarian);

        userRepository.deleteById(librarian.getId());

        assertThat(userRepository.findAll(), containsInAnyOrder(martin));
    }

    @Test
    public void testAddingUsersWithSameEmail() {
        User martin = createTestUserMartin();
        entityManager.persist(martin);
        User boris = createTestUserBoris();
        boris.setEmail(martin.getEmail());
        assertThrows(PersistenceException.class, () -> entityManager.persist(boris));
    }

    @Test(expected = DataAccessException.class)
    public void saveNull(){
        userRepository.save(null);
    }

    @Test(expected = DataAccessException.class)
    public void saveAllNull(){
        userRepository.saveAll(null);
    }

    @Test(expected = DataAccessException.class)
    public void testDeleteNull() {
        userRepository.delete(null);
    }

    @Test(expected = DataAccessException.class)
    public void testDeleteAllNull() {
        userRepository.deleteAll(null);
    }

    @Test(expected = DataAccessException.class)
    public void testDeleteByNonExistingId() {
        userRepository.deleteById(Long.MAX_VALUE);
    }

    @Test(expected = DataAccessException.class)
    public void testFindByIdNull() {
        userRepository.findById(null);
    }

    @Test(expected = DataAccessException.class)
    public void testDeleteByIdNull() {
        userRepository.deleteById(null);
    }

    @Test(expected = DataAccessException.class)
    public void testExistsByIdNull() {
        userRepository.existsById(null);
    }

    @Test
    public void testFindByNonExistingId() {
        Assert.assertEquals(Optional.empty(), userRepository.findById(Long.MAX_VALUE));
    }

    private User createTestUserMartin() {
        User martin = new User();
        martin.setFirstName("Martin");
        martin.setLastName("Novak");
        martin.setEmail("mail@mail.com");
        martin.setPasswordHash("password");
        return martin;
    }

    private User createTestUserBoris() {
        User boris = new User();
        boris.setFirstName("Boris");
        boris.setLastName("Chan");
        boris.setEmail("boris@mail.com");
        boris.setPasswordHash("password");
        return boris;
    }
}
