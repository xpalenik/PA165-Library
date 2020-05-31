package cz.muni.fi.pa165.library.repositories;

import cz.muni.fi.pa165.library.entities.SingleLoan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author Martin Páleník 359817
 * based on http://zetcode.com/springboot/crudrepository/
 */
@Repository
public interface SingleLoanRepository extends CrudRepository<SingleLoan, Long> {
    List<SingleLoan> findAll();
}
