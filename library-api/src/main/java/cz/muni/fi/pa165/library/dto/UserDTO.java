package cz.muni.fi.pa165.library.dto;

/**
 * @author Katarína Hermanová
 * UČO 433511
 * Github katHermanova
 */
public class UserDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String passwordHash;
    private boolean isLibrarian;

    public UserDTO() {

    }

    public UserDTO(long id, String firstName, String lastName, String email, String passwordHash, boolean isLibrarian) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.passwordHash = passwordHash;
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

    public boolean isLibrarian() {
        return isLibrarian;
    }

    public void setLibrarian(boolean librarian) {
        isLibrarian = librarian;
    }

    //equals() and hashCode() from https://github.com/fi-muni/PA165/blob/master/eshop-api/src/main/java/cz/fi/muni/pa165/dto/UserDTO.java
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        UserDTO other = (UserDTO) o;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
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
                ", isLibrarian='" + isLibrarian + '\'' +
                '}';
    }
}
