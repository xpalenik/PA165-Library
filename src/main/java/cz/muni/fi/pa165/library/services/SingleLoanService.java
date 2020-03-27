package cz.muni.fi.pa165.library.services;

import cz.muni.fi.pa165.library.entities.SingleLoan;
import cz.muni.fi.pa165.library.repositories.SingleLoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/** @author Martin Páleník 359817
 *  based on http://zetcode.com/springboot/crudrepository/
 */

@Service
public class SingleLoanService {

    @Autowired
    private SingleLoanRepository singleLoanRepository;

    public List<SingleLoan> findAll() {
        return (List<SingleLoan>) singleLoanRepository.findAll();
    }

    public Long count() {
        return singleLoanRepository.count();
    }

    public void deleteById(Long userId) {
        singleLoanRepository.deleteById(userId);
    }
}