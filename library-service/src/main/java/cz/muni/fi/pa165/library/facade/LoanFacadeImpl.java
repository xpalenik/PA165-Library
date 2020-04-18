package cz.muni.fi.pa165.library.facade;

import cz.muni.fi.pa165.library.services.MappingService;
import cz.muni.fi.pa165.library.services.SingleLoanService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}