package com.manage.book.manage.loan;

import com.manage.book.manage.book.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanHistoryService {
    final private LoanHistoryRepository loanHistoryRepository;

    public List<LoanHistory> findHistories(Book book) {
        return loanHistoryRepository.findAllByBookOrderByLoanDateDesc(book);
    }
}
