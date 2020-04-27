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

/** @author Martin Páleník 359817 */
@Service
@Transactional
public abstract class LoanFacadeImpl implements LoanFacade{

    private final MappingService mappingService;
    private final SingleLoanService singleLoanService;

    protected LoanFacadeImpl(MappingService mappingService, SingleLoanService singleLoanService) {
        this.mappingService = mappingService;
        this.singleLoanService = singleLoanService;
    }

    @Override
    public long borrowBook(SingleLoanDTO singleLoan) {
        return singleLoanService.createSingleLoan(mappingService.mapTo(singleLoan, SingleLoan.class));
    }

    @Override
    public List<Long> borrowBooks(LoanDTO loans) {
        List loanIds = new ArrayList();
        for (SingleLoanDTO singleLoanDto : loans.getLoans()){
            loanIds.add(
                    singleLoanService.createSingleLoan(mappingService.mapTo(singleLoanDto, SingleLoan.class))
            );
        }
        return loanIds;
    }
}