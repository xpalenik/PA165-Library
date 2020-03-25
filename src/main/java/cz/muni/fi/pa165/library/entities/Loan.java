package cz.muni.fi.pa165.library.entities;

import javax.persistence.*;
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
}
