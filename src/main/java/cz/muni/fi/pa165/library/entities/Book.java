package cz.muni.fi.pa165.library.entities;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Petr Janik 485122
 * @since 09.03.2020
 */

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    private String author;

    @OneToMany(mappedBy = "book")
    Set<SingleLoan> singleLoans;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Set<SingleLoan> getSingleLoans() {
        return singleLoans;
    }

    public void setSingleLoans(Set<SingleLoan> singleLoans) {
        this.singleLoans = singleLoans;
    }
}
