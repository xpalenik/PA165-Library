package cz.muni.fi.pa165.library.facade;

import cz.muni.fi.pa165.library.dto.BookDTO;
import cz.muni.fi.pa165.library.dto.SingleLoanDTO;
import cz.muni.fi.pa165.library.dto.UserDTO;
import cz.muni.fi.pa165.library.entities.Book;
import cz.muni.fi.pa165.library.entities.SingleLoan;
import cz.muni.fi.pa165.library.entities.User;
import cz.muni.fi.pa165.library.services.MappingService;
import cz.muni.fi.pa165.library.services.SingleLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/** @author Martin Páleník 359817 */
@Service
@Transactional
public class LoanFacadeImpl implements LoanFacade{

    @Autowired
    private final MappingService mappingService;

    @Autowired
    private final SingleLoanService singleLoanService;

    public LoanFacadeImpl(MappingService mappingService, SingleLoanService singleLoanService) {
        this.mappingService = mappingService;
        this.singleLoanService = singleLoanService;
    }

    @Override
    public long borrowBook(SingleLoanDTO singleLoanInfo) {
        return singleLoanService.createSingleLoan(mappingService.mapTo(singleLoanInfo, SingleLoan.class));
    }

    @Override
    public void returnBook(SingleLoanDTO returnInfo) {
        Optional<SingleLoan> loan = singleLoanService.findById(returnInfo.getId());

        if (loan.isPresent()){
            singleLoanService.returnBook(loan.get(), returnInfo.getReturnedAt(), returnInfo.getReturnCondition());
        } else {
            throw new NoSuchElementException("No loan with id " + returnInfo.getId() + " has been found.");
        }
    }

    @Override
    public List<SingleLoanDTO> getLoansForUser(UserDTO userDto) {

        List <SingleLoan> results = singleLoanService.getLoansForUser(
                mappingService.mapTo(userDto, User.class)
        );

        List<SingleLoanDTO> resultsDto = new ArrayList<>();
        for (SingleLoan result : results){
            resultsDto.add(
                    mappingService.mapTo(result, SingleLoanDTO.class)
            );
        }

        return resultsDto;
    }

    @Override
    public List<SingleLoanDTO> getLoansForBook(BookDTO bookDto) {
        List <SingleLoan> results = singleLoanService.getLoansForBook(
                mappingService.mapTo(bookDto, Book.class)
        );

        List<SingleLoanDTO> resultsDto = new ArrayList<>();
        for (SingleLoan result : results){
            resultsDto.add(
                    mappingService.mapTo(result, SingleLoanDTO.class)
            );
        }

        return resultsDto;
    }

    @Override
    public List<SingleLoanDTO> getAllSingleLoans() {

        List<SingleLoanDTO> resultsDto = new ArrayList<>();
        for (SingleLoan result : singleLoanService.findAll()){
            resultsDto.add(
                    mappingService.mapTo(result, SingleLoanDTO.class)
            );
        }

        return resultsDto;
    }

    @Override
    public SingleLoanDTO getSingleLoanById(long id) {
        Optional<SingleLoan> singleLoan = singleLoanService.findById(id);

        if (singleLoan.isPresent()){
            SingleLoanDTO mapped =  mappingService.mapTo(
                    singleLoan.get(),
                    SingleLoanDTO.class);
            return mapped;
        } else {
            return null;
        }
    }
}