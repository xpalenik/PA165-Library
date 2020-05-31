package cz.muni.fi.pa165.library.controllers;

import cz.muni.fi.pa165.library.dto.BookDTO;
import cz.muni.fi.pa165.library.dto.SingleLoanDTO;
import cz.muni.fi.pa165.library.dto.UserDTO;
import cz.muni.fi.pa165.library.facade.LoanFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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

    @PutMapping(value = "/loan_update/{id}")
    public void returnBook(@PathVariable long id, @RequestBody SingleLoanDTO returnInfo) {
        loanFacade.returnBook(returnInfo);
    }

    @GetMapping(value = "/loansByUser/{userDto}")
    public List<SingleLoanDTO> getLoansForUser(@PathVariable UserDTO userDto) {
        return loanFacade.getLoansForUser(userDto);
    }

    @GetMapping(value = "/loansByBook/{bookDto}")
    public List<SingleLoanDTO> getLoansForBook(@PathVariable BookDTO bookDto) {
        return loanFacade.getLoansForBook(bookDto);
    }

    @GetMapping(value = "/loans")
    public List<SingleLoanDTO> getAllSingleLoans() {
        return loanFacade.getAllSingleLoans();
    }

    @GetMapping(value = "/loan_id/{id}")
    public SingleLoanDTO getSingleLoanById(@PathVariable long id) {
        return loanFacade.getSingleLoanById(id);
    }

    @GetMapping(value = "/loans_count")
    public Long count() {
        return loanFacade.count();
    }

    @DeleteMapping("/delete/loan/{id}")
    public void deleteById(@PathVariable long id) {
        loanFacade.deleteById(id);
    }
}
