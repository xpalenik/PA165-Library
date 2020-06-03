package cz.muni.fi.pa165.library.repositories;

import cz.muni.fi.pa165.library.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * DAO layer interface
 *
 * @author Katarína Hermanová
 * UČO 433511
 * Github katHermanova
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     *
     * @return list of all existing users
     */
    List<User> findAll();
}
