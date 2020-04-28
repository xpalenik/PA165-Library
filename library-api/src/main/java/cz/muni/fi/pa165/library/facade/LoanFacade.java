package cz.muni.fi.pa165.library.facade;

import cz.muni.fi.pa165.library.dto.BookDTO;
import cz.muni.fi.pa165.library.dto.LoanDTO;
import cz.muni.fi.pa165.library.dto.SingleLoanDTO;
import cz.muni.fi.pa165.library.dto.UserDTO;

import java.util.List;

/** @author Martin Páleník 359817 */
public interface LoanFacade {

    public long borrowBook(SingleLoanDTO singleLoanInfo);
    public void returnBook(SingleLoanDTO returnInfo);

    // if time permits
    public List<Long> borrowBooks(LoanDTO loans);
    public void returnBooks(LoanDTO loans);

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