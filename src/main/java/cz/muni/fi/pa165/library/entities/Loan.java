package cz.muni.fi.pa165.library.entities;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

/** @author Martin Páleník 359817 */

@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany
    Set<SingleLoan> loans;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return id == loan.id &&
                Objects.equals(loans, loan.loans);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, loans);
    }
}
