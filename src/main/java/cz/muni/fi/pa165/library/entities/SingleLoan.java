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

    @NotNull
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    private LocalDateTime registeredAt;

    private LocalDateTime returnedAt;

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
                Objects.equals(returnedAt, that.returnedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, book, user, registeredAt, returnedAt);
    }

    @Override
    public String toString() {
        return "SingleLoan{" +
                "id=" + id +
                ", book=" + book +
                ", member=" + user +
                ", registeredAt=" + registeredAt +
                '}';
    }
}
