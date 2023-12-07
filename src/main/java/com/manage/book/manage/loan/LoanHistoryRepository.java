package com.manage.book.manage.loan;

import com.manage.book.manage.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanHistoryRepository extends JpaRepository<LoanHistory, Long> {
    List<LoanHistory> findAllByBookOrderByLoanDateDesc(Book book);

    void deleteByBook(Book book);
}
