package cz.muni.fi.pa165.library.dto;

//Importing dummy class until UserDTO is implemented.
import cz.muni.fi.pa165.library.dto.SingleLoanDTO.UserDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** @author Martin Páleník 359817
 * Modelled according to cz.fi.muni.pa165.dto.OrderDTO
 * */

public class LoanDto {

    private long id;
    private List<SingleLoanDTO> loans = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<SingleLoanDTO> getLoans() {
        return loans;
    }

    public void setLoans(List<SingleLoanDTO> loans) {
        this.loans = loans;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoanDto)) return false;
        LoanDto loanDto = (LoanDto) o;
        return id == loanDto.id &&
                loans.equals(loanDto.loans);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, loans);
    }

    @Override
    public String toString() {
        return "LoanDto{" +
                "id=" + id +
                ", loans=" + loans +
                '}';
    }
}
