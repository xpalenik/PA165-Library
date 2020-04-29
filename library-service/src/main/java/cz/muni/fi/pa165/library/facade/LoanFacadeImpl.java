package cz.muni.fi.pa165.library.facade;

import cz.muni.fi.pa165.library.dto.LoanDTO;
import cz.muni.fi.pa165.library.dto.SingleLoanDTO;
import cz.muni.fi.pa165.library.entities.SingleLoan;
import cz.muni.fi.pa165.library.services.MappingService;
import cz.muni.fi.pa165.library.services.SingleLoanService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/** @author Martin Páleník 359817 */
@Service
@Transactional
public class LoanFacadeImpl implements LoanFacade{

    private final MappingService mappingService;
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
        }
    }
}