package com.manage.book.manage.book;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity @Table(name = "book")
@Getter @Setter @ToString
public class Book {
    @Id @Column(name = "book_isbn", length = 13)
    private String bookIsbn;

    @Column(name = "book_nm", nullable = false, length = 20)
    private String bookNm;

    @Column(name = "book_author", nullable = false, length = 20)
    private String bookAuthor;

    @Column(name = "book_publisher", nullable = false, length = 20)
    private String bookPublisher;

    @Column(name = "book_loanable")
    private boolean isLoanable;

    @Column(name = "registrant", length = 20)
    private String registrant;

    @Column(name = "reg_date", length = 14)
    private String regDate;

    @Column(name="modifier", length = 20)
    private String modifier;

    @Column(name = "mod_date", length = 14)
    private String modDate;

    @Column(name = "loan_receiver", length = 20)
    private String loanReceiver;

    @Column(name = "loan_phone", length = 11)
    private String loanPhone;

    @Column(name = "loan_start", length = 8)
    private String loanStart;

    @Column(name = "loan_end", length = 8)
    private String loanEnd;

    @Column(name = "loan_reason", length = 20)
    private String loanReason;
}
