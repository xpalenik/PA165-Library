package cz.muni.fi.pa165.library.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Petr Janik 485122
 * @since 25.03.2020
 */
@Entity
public class SingleLoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    private LocalDateTime registeredAt;

    private LocalDateTime returnedAt;

    /** @author Martin Páleník 359817
     * Condition of the returned book.
     * generated getters/setters
     * updated hashCode/equals and toString
     */
    private String returnCondition;

    public SingleLoan() {

    }

    public SingleLoan(Book book, User user, LocalDateTime registeredAt) {
        this.book = book;
        this.user = user;
        this.registeredAt = registeredAt;
    }

    public String getReturnCondition() {
        return returnCondition;
    }

    public void setReturnCondition(String returnCondition) {
        this.returnCondition = returnCondition;
    }

    public LocalDateTime getReturnedAt() { return returnedAt; }

    public void setReturnedAt(LocalDateTime returnedAt) { this.returnedAt = returnedAt; }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(LocalDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SingleLoan that = (SingleLoan) o;
        return id == that.id &&
                Objects.equals(book, that.book) &&
                Objects.equals(user, that.user) &&
                Objects.equals(registeredAt, that.registeredAt) &&
                Objects.equals(returnedAt, that.returnedAt) &&
                Objects.equals(returnCondition, that.returnCondition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, book, user, registeredAt, returnedAt, returnCondition);
    }
}