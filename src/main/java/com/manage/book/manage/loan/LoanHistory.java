package com.manage.book.manage.loan;

import com.manage.book.manage.book.Book;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity @Table(name = "loan_history")
@Getter @Setter @ToString
public class LoanHistory {

    @Id @Column(name = "loan_history_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long loanHistoryId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_isbn")
    private Book book;

    @Column(name = "loan_date")
    private String loanDate;

    @Column(name = "loan_receiver")
    private String loanReceiver;

    @Column(name = "loan_phone")
    private String loanPhone;

    @Column(name = "loan_reason")
    private String loanReason;

    @Column(name = "loan_manager")
    private String loanManager;

    @Column(name = "loanable")
    private boolean loanable;

}