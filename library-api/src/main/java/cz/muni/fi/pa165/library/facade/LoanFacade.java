package cz.muni.fi.pa165.library.facade;

import cz.muni.fi.pa165.library.dto.BookDTO;
import cz.muni.fi.pa165.library.dto.SingleLoanDTO;
import cz.muni.fi.pa165.library.dto.UserDTO;
import java.util.List;

/** @author Martin Páleník 359817 */
public interface LoanFacade {

    /**
     * Register a new loan.
     * The loan is an association between one user and one book.
     * Other information may be provided by the DTO.
     * @param singleLoanInfo
     * @return id of the created loan
     */
    long borrowBook(SingleLoanDTO singleLoanInfo);

    /**
     * Return a book for an existing loan.
     * Records the provided time of return
     * and conditon of the returned book.
     * @param returnInfo
     * @return id of the created loan
     */
    void returnBook(SingleLoanDTO returnInfo);

    /**
     * For the given user return all his loans (current or past).
     * Answers "what a member borrowed and when".
     * @return list of loans for the given user
     */
    List<SingleLoanDTO> getLoansForUser(UserDTO userDto);

    /**
     * For the given book return all its loans (current or past).
     * Answers "who borrowed a certain book".
     * Answers "what condition they returned the book in".
     * @return list of loans for the given book
     */
    List<SingleLoanDTO> getLoansForBook(BookDTO bookDto);

    /**
     * Get all loans in the system, including the ones
     * with already returned book.
     *
     * @return list of all loans ever registered
     */
    List<SingleLoanDTO> getAllSingleLoans();

    /**
     * Get a loan by id.
     * @param id
     * @return loan if exists, null otherwise
     */
    SingleLoanDTO getSingleLoanById(long id);

    /**
     * Output the number of loans ever registered by the system.
     * @return a number of loans (may be zero)
     */
    Long count();

    /**
     * Remove a loan by its id.
     * Note: calls CrudRepository directly
     * @param loanId
     * @throws IllegalArgumentException if "id" is invalid
     */
    void deleteById(long loanId);
}