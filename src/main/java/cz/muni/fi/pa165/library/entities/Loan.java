package cz.muni.fi.pa165.library.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

/** @author Martin Páleník 359817 */
/**
 * This entity populates multiple SingleLoans.
 *
 * It implements the following assignment condition:
 * "Take into account that a person can borrow multiple books in a single loan."
 *
 * Although it might be useful for performance or usability
 * it is not necessary for the functionality of application as
 * the loan functionality is fully implemented by SingleLoan.
 *
 */
@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @OneToMany
    private Collection<SingleLoan> loans;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", loans=" + loans +
                '}';
    }
}
