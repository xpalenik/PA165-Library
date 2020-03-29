package cz.muni.fi.pa165.library.services;

import cz.muni.fi.pa165.library.entities.SingleLoan;
import cz.muni.fi.pa165.library.repositories.SingleLoanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/** @author Martin Páleník 359817
 *  based on http://zetcode.com/springboot/crudrepository/
 *
 *  This represents the business logic of the
 *  loans of book or books by a member.
 */

@Service
public class SingleLoanService {

    private SingleLoanRepository singleLoanRepository;

    /**
     * Constructor creates single loan between a member and a book
     * @param singleLoanRepository
     */
    public SingleLoanService(SingleLoanRepository singleLoanRepository) {
        this.singleLoanRepository = singleLoanRepository;
    }

    /**
     * Returns all loans, including the ones
     * where the book has been already returned.
     *
     * @return list of all loans
     */
    public List<SingleLoan> findAll() {
        return (List<SingleLoan>) singleLoanRepository.findAll();
    }

    /**
     * Returns number of loans (including past loans)
     * that has ever been entered in the system.
     *
     * @return number of all loans
     */
    public Long count() {
        return singleLoanRepository.count();
    }

    /**
     * Deletes a specific SingleLoan with the given "id"
     * @param userId is ID of SingleLoan we are looking for
     * @throws IllegalArgumentException if "id" is less than 0
     */
    public void deleteById(Long userId) {
        singleLoanRepository.deleteById(userId);
    }

}
