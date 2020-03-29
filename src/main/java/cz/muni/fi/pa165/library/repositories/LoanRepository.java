package cz.muni.fi.pa165.library.repositories;

import cz.muni.fi.pa165.library.entities.Loan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Petr Janik 485122
 * @since 29.03.2020
 */
public interface LoanRepository extends CrudRepository<Loan, Long> {
    List<Loan> findAll();
}
