package com.manage.book.manage.loan;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class LoanHistoryDto {
    public String bookIsbn;
    public String loanDate;
    public String loanReceiver;
    public String loanPhone;
    public String loanReason;
    public String loanManager;
    public boolean loanable;

    public static LoanHistoryDto of(LoanHistory loanHistory) {
        LoanHistoryDto loanHistoryDto = new LoanHistoryDto();
        loanHistoryDto.setBookIsbn(loanHistory.getBook().getBookIsbn());
        loanHistoryDto.setLoanDate(loanHistory.getLoanDate());
        loanHistoryDto.setLoanReceiver(loanHistory.getLoanReceiver());
        loanHistoryDto.setLoanPhone(loanHistory.getLoanPhone());
        loanHistoryDto.setLoanReason(loanHistory.getLoanReason());
        loanHistoryDto.setLoanManager(loanHistory.getLoanManager());
        loanHistoryDto.setLoanable(loanHistory.isLoanable());
        return loanHistoryDto;
    }
}
