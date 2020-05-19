package cz.muni.fi.pa165.library.controllers;

import cz.muni.fi.pa165.library.dto.BookDTO;
import cz.muni.fi.pa165.library.dto.SingleLoanDTO;
import cz.muni.fi.pa165.library.dto.UserDTO;
import cz.muni.fi.pa165.library.facade.LoanFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Martin Páleník 359817
 * based on http://zetcode.com/springboot/crudrepository/
 */

@RestController
@Transactional
public class SingleLoanController extends AbstractController {

    @Autowired
    private LoanFacade loanFacade;

    @PostMapping(value = "/loans")
    public long borrowBook(@RequestBody SingleLoanDTO singleLoanInfo){
        return loanFacade.borrowBook(singleLoanInfo);
    }

    @PutMapping(value = "/loans/{id}")
    public void returnBook(@PathVariable long id, @RequestBody SingleLoanDTO returnInfo) {
        loanFacade.returnBook(returnInfo);
    }

    @GetMapping(value = "/loans/{userDto}")
    public List<SingleLoanDTO> getLoansForUser(@PathVariable UserDTO userDto) {
        return loanFacade.getLoansForUser(userDto);
    }

    @GetMapping(value = "/loans/{bookDto}")
    public List<SingleLoanDTO> getLoansForBook(@PathVariable BookDTO bookDto) {
        return loanFacade.getLoansForBook(bookDto);
    }

    @GetMapping(value = "/loans")
    public List<SingleLoanDTO> getAllSingleLoans() {
        return loanFacade.getAllSingleLoans();
    }

    @GetMapping(value = "/loans/{id}")
    public SingleLoanDTO getSingleLoanById(@PathVariable long id) {
        return loanFacade.getSingleLoanById(id);
    }

    @GetMapping(value = "/loans/count")
    public Long count() {
        return loanFacade.count();
    }

    @DeleteMapping("/loans/{id}")
    public void deleteById(@PathVariable long loanId) {
        loanFacade.deleteById(loanId);
    }
}
