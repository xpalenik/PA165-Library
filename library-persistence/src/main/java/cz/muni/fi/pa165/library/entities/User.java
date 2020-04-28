package cz.muni.fi.pa165.library.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

/**
 * class for User entity
 *
 * @author Katarína Hermanová
 * UČO 433511
 * Github katHermanova
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column(unique = true)
    private String email;

    private String passwordHash;

    @OneToMany(mappedBy = "user")
    private Collection<SingleLoan> singleLoans;

    @Column
    private boolean isLibrarian;

    public User() {
    }

    public User(String firstName, String lastName, String email, boolean isLibrarian) {
        if (firstName == null || firstName.isEmpty() || lastName == null
                || lastName.isEmpty() || email == null || email.isEmpty()) {
            throw new IllegalArgumentException("User argument is null.");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.isLibrarian = isLibrarian;
    }

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Collection<SingleLoan> getSingleLoans() {
        return singleLoans;
    }

    public void setSingleLoans(Collection<SingleLoan> singleLoans) {
        this.singleLoans = singleLoans;
    }

    public boolean isLibrarian() {
        return isLibrarian;
    }

    public void setLibrarian(boolean librarian) {
        isLibrarian = librarian;
    }

    //equals and hashCode() from https://github.com/fi-muni/PA165/blob/master/eshop-persistence/src/main/java/cz/fi/muni/pa165/entity/User.java
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (!(o instanceof User))
            return false;
        User other = (User) o;
        if (email == null) {
            if (other.getEmail() != null)
                return false;
        } else if (!email.equals(other.getEmail()))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", singleLoans=" + singleLoans + '\'' +
                ", isLibrarian=" + isLibrarian + '\'' +
                '}';
    }
}
