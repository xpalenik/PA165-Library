package cz.muni.fi.pa165.library.repositories;

import cz.muni.fi.pa165.library.entities.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author Petr Janik 485122
 * @since 09.03.2020
 *
 * Data access object interface
 */
@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    /**
     *
     * @return list of all existing books
     */
    List<Book> findAll();
}
