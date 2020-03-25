package cz.muni.fi.pa165.library.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

/**
 * @author Katarína Hermanová
 * UČO 433511
 * Github katHermanova
 */
@Entity
public class Member {

    @Id
    @GeneratedValue
    private long memberId;
    private String firstName;
    private String surname;
    private boolean isLibrarian;

    @OneToMany(mappedBy = "member")
    Set<SingleLoan> singleLoans;

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean isLibrarian() {
        return isLibrarian;
    }

    public void setLibrarian(boolean librarian) {
        isLibrarian = librarian;
    }
}
