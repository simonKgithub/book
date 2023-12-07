package com.manage.book.manage.book;

import com.manage.book.member.Member;
import com.manage.book.utils.CommonUtils;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity @Table(name = "book")
@Getter @Setter @ToString
public class Book {
    @Id @Column(name = "book_isbn")
    private String bookIsbn;

    @Column(name = "book_nm")
    private String bookNm;

    @Column(name = "book_author")
    private String bookAuthor;

    @Column(name = "book_publisher")
    private String bookPublisher;

    @Column(name = "book_loanable")
    private boolean isLoanable;

    @Column(name = "registrant")
    private String registrant;

    @Column(name = "reg_date")
    private String regDate;

    @Column(name="modifier")
    private String modifier;

    @Column(name = "mod_date")
    private String modDate;

    @Column(name = "loan_receiver")
    private String loanReceiver;

    @Column(name = "loan_phone")
    private String loanPhone;

    @Column(name = "loan_start")
    private String loanStart;

    @Column(name = "loan_end")
    private String loanEnd;

    @Column(name = "loan_reason")
    private String loanReason;
}
