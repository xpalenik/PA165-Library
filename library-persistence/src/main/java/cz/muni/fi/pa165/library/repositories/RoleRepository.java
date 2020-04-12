package cz.muni.fi.pa165.library.repositories;

import cz.muni.fi.pa165.library.entities.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Petr Janik 485122
 * @since 09.03.2020
 */
public interface RoleRepository extends CrudRepository<Role, Long> {
}
