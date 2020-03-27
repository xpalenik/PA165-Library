package cz.muni.fi.pa165.library.controllers;

import cz.muni.fi.pa165.library.entities.SingleLoan;
import cz.muni.fi.pa165.library.services.SingleLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/** @author Martin Páleník 359817
 *  based on http://zetcode.com/springboot/crudrepository/
 */

@RestController
@Transactional
public class SingleLoanController extends AbstractController {

    @Autowired
    private SingleLoanService singleLoanService;

    @GetMapping("/loans")
    public List<SingleLoan> allSingleLoans() {
        return singleLoanService.findAll();
    }

    @GetMapping("/loans/count")
    public Long count() {
        return singleLoanService.count();
    }

    @DeleteMapping("/loans/{id}")
    public void delete(@PathVariable String id) {
        Long singleLoanId = Long.parseLong(id);
        singleLoanService.deleteById(singleLoanId);
    }
}