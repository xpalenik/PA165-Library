package cz.muni.fi.pa165.library.facade;

import cz.muni.fi.pa165.library.dto.BookDTO;
import cz.muni.fi.pa165.library.dto.SingleLoanDTO;
import cz.muni.fi.pa165.library.dto.SingleLoanDTO.UserDTO;

import java.util.List;

/** @author Martin Páleník 359817 */
public interface SingleLoanFacade {

    /** The time of loan will be set to now() */
    public void borrowBook(SingleLoanDTO singleLoanDto);

    /** The time of return will be set to now() */
    public void returnBook(SingleLoanDTO singleLoanDto);

    /**
     * For the given user return all his loans (current or past).
     * Answers "what a member borrowed and when".
     * @return list of loans for the given user
     */
    public List<SingleLoanDTO> getLoansForUser(UserDTO userDto);

    /**
     * For the given book return all its loans (current or past).
     * Answers "who borrowed a certain book".
     * Answers "what condition they returned the book in".
     * @return list of loans for the given book
     */
    public List<SingleLoanDTO> getLoansForBook(BookDTO bookDto);

    public List<SingleLoanDTO> getAllSingleLoans();
    public SingleLoanDTO getSingleLoanById(long id);

}