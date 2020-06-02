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
import java.util.Arrays;
import java.util.Optional;

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
        User martin = new User("Martin", "Novak", "mail@mail.com", false);
        martin.setPasswordHash("password");
        userRepository.save(martin);

        // then
        Assert.assertNotNull(martin.getId());
        Assert.assertTrue(userRepository.existsById(martin.getId()));
        Assert.assertEquals(Optional.of(martin), userRepository.findById(martin.getId()));
    }

    @Test
    public void findAll() {
        // given
        User martin = new User("Martin", "Novak", "mail@mail.com", false);
        martin.setPasswordHash("password");
        userRepository.save(martin);

        User librarian = new User("Boris", "Chan", "boris@mail.com", true);
        librarian.setPasswordHash("password");
        userRepository.save(librarian);

        Assert.assertNotNull(martin.getId());
        Assert.assertNotNull(librarian.getId());
        Assert.assertEquals(2, userRepository.count());
        Assert.assertEquals(Arrays.asList(martin, librarian), userRepository.findAll());
    }

    @Test
    public void deleteById() {
        // given
        User martin = new User("Martin", "Novak", "mail@mail.com", false);
        martin.setPasswordHash("password");
        userRepository.save(martin);

        User librarian = new User("Boris", "Chan", "boris@mail.com", true);
        librarian.setPasswordHash("password");
        userRepository.save(librarian);

        Assert.assertNotNull(martin.getId());
        Assert.assertNotNull(librarian.getId());
        Assert.assertEquals(2, userRepository.count());
        Assert.assertEquals(Arrays.asList(martin, librarian), userRepository.findAll());

        userRepository.deleteById(librarian.getId());

        Assert.assertEquals(1, userRepository.count());
        Assert.assertEquals(Arrays.asList(martin), userRepository.findAll());
    }

    @Test(expected = DataAccessException.class)
    public void testAddingUsersWithSameEmail() {
        User martin = new User("Martin", "Novak", "mail@mail.com", false);
        martin.setPasswordHash("password");
        userRepository.save(martin);

        User boris = new User("Boris", "Chan", martin.getEmail(), true);
        boris.setPasswordHash("password");

        userRepository.save(boris);
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
}
