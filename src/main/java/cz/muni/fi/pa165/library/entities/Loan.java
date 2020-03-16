package cz.muni.fi.pa165.library.entities;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

class Member{};

/** @author Martin Páleník 359817 */

public class Loan {
    Long id;
    LocalDateTime borrowedAt;
    HashMap<Long, String> returnedBookCondition;

    Member member;
    List<Book> books;

    public Loan(Member member, List<Book> books) {
        this.member = member;

        for (Book book: books){
            this.books.add(book);
            returnedBookCondition.put(book.getId(),null);
        }
    }

    public void returnBook(Long id, String condition){
        returnedBookCondition.put(id,condition);
    }
}