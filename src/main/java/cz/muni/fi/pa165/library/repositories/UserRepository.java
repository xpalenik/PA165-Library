package cz.muni.fi.pa165.library.repositories;

import cz.muni.fi.pa165.library.entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * DAO layer interface
 *
 * @author Katarína Hermanová
 * UČO 433511
 * Github katHermanova
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
}
