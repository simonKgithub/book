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

    @Column(name = "loan_date", nullable = false, length = 14)
    private String loanDate;

    @Column(name = "loan_receiver", nullable = false, length = 20)
    private String loanReceiver;

    @Column(name = "loan_phone", nullable = false, length = 11)
    private String loanPhone;

    @Column(name = "loan_reason", length = 20)
    private String loanReason;

    @Column(name = "loan_manager", length = 20)
    private String loanManager;

    @Column(name = "loanable")
    private boolean loanable;

}