package com.manage.book.manage.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

@Getter @Setter @ToString
public class BookDto {
    @NotBlank
    @Length(min = 10, max = 13, message = "10~13 자리 숫자를 입력해주세요.")
    public String bookIsbn;
    @NotEmpty(message = "도서명을 입력해주세요.")
    public String bookNm;
    @NotEmpty(message = "저자명을 입력해주세요.")
    public String bookAuthor;
    @NotEmpty(message = "출판사를 입력해주세요.")
    public String bookPublisher;


    public boolean isLoanable;
    public String registrant;
    public String regDate;
    public String modifier;
    public String mod_date;
    public String loanStart;
    public String loanEnd;

    public static BookDto of(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setBookIsbn(book.getBookIsbn());
        bookDto.setBookNm(book.getBookNm());
        bookDto.setBookAuthor(book.getBookAuthor());
        bookDto.setBookPublisher(book.getBookPublisher());
        bookDto.setLoanable(book.isLoanable());
        bookDto.setRegistrant(book.getRegistrant());
        bookDto.setRegDate(book.getRegDate());
        bookDto.setLoanStart(book.getLoanStart());
        bookDto.setLoanEnd(book.getLoanEnd());
        return bookDto;
    }
}
