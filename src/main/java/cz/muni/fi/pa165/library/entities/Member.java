package cz.muni.fi.pa165.library.entities;

import javax.persistence.*;
import java.util.Set;

/**
 * class for Member entity
 *
 * @author Katarína Hermanová
 * UČO 433511
 * Github katHermanova
 */
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String surname;
    private boolean isLibrarian;
    @OneToMany(mappedBy = "member")
    private Set<SingleLoan> singleLoans;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Set<SingleLoan> getSingleLoans() {
        return singleLoans;
    }

    public void setSingleLoans(Set<SingleLoan> singleLoans) {
        this.singleLoans = singleLoans;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", firstName='" + firstName +
                "', surname='" + surname +
                "', isLibrarian=" + isLibrarian +"}";
    }
}
