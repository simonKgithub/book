package com.manage.book.manage.loan;

import com.manage.book.manage.book.Book;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

@Getter @Setter @ToString
public class LoanDto {
    @NotBlank
    public String bookIsbn;

    @NotBlank(message = "대출자는 필수입력입니다.")
    public String loanReceiver;

    @NotBlank(message = "휴대폰 번호는 필수입력입니다.")
    public String loanPhone;

    @Length(max=8, min = 8, message = "8자리로 입력해주세요.")
    public String loanStart;

    @Length(max=8, min = 8, message = "8자리로 입력해주세요.")
    public String loanEnd;

    public String loanReason;

    public boolean isLoanable;

    public String loanManager;

    public static LoanDto of(Book book) {
        LoanDto loanDto = new LoanDto();
        loanDto.setBookIsbn(book.getBookIsbn());
        loanDto.setLoanStart(book.getLoanStart());
        loanDto.setLoanEnd(book.getLoanEnd());
        loanDto.setLoanReceiver(book.getLoanReceiver());
        loanDto.setLoanPhone(book.getLoanPhone());
        loanDto.setLoanReason(book.getLoanReason());
        return loanDto;
    }
}