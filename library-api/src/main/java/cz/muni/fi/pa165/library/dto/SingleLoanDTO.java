package cz.muni.fi.pa165.library.dto;

import java.time.LocalDateTime;
import java.util.Objects;

/** @author Martin Páleník 359817 */
public class SingleLoanDTO {

    //Dummy class while UserDTO is implemented.
    public class UserDTO {}

    private long id;
    private BookDTO book;
    private UserDTO user;
    private LocalDateTime registeredAt;
    private LocalDateTime returnedAt;
    private String returnCondition;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BookDTO getBook() {
        return book;
    }

    public void setBook(BookDTO book) {
        this.book = book;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(LocalDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }

    public LocalDateTime getReturnedAt() {
        return returnedAt;
    }

    public void setReturnedAt(LocalDateTime returnedAt) {
        this.returnedAt = returnedAt;
    }

    public String getReturnCondition() {
        return returnCondition;
    }

    public void setReturnCondition(String returnCondition) {
        this.returnCondition = returnCondition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SingleLoanDTO)) return false;
        SingleLoanDTO that = (SingleLoanDTO) o;
        return id == that.id &&
                book.equals(that.book) &&
                user.equals(that.user) &&
                registeredAt.equals(that.registeredAt) &&
                Objects.equals(returnedAt, that.returnedAt) &&
                Objects.equals(returnCondition, that.returnCondition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, book, user, registeredAt, returnedAt, returnCondition);
    }

    @Override
    public String toString() {
        return "SingleLoanDTO{" +
                "id=" + id +
                ", book=" + book +
                ", user=" + user +
                ", registeredAt=" + registeredAt +
                ", returnedAt=" + returnedAt +
                ", returnCondition='" + returnCondition + '\'' +
                '}';
    }
}
