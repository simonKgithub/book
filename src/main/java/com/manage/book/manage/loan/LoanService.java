package com.manage.book.manage.loan;

import com.manage.book.manage.book.Book;
import com.manage.book.manage.book.BookRepository;
import com.manage.book.member.Member;
import com.manage.book.utils.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoanService {

    final private BookRepository bookRepository;
    final private LoanHistoryRepository loanHistoryRepository;

    /**
     * 도서 반납
     * @param loanDto
     * @return
     */
    public Book returnBook(LoanDto loanDto) {
        Book targetBook = bookRepository.findByBookIsbn(loanDto.getBookIsbn());
        targetBook.setLoanable(true);

        //도서 반남 이력 등록(순서유의, loanDto의 값 전에 실행해야한다.)
        LoanHistory loanHistory = this.makeHistoryEntity(targetBook, new LoanHistory());
        loanHistoryRepository.save(loanHistory);

        this.toEntity(targetBook, loanDto);
        return bookRepository.save(targetBook);
    }

    /**
     * 도서 대출
     * @param loanDto
     * @return
     */
    public Book loanBook(LoanDto loanDto) {
        Book targetBook = bookRepository.findByBookIsbn(loanDto.getBookIsbn());
        targetBook.setLoanable(false);

        this.toEntity(targetBook, loanDto);
        //도서 대출 이력 등록(순서유의, loanDto의 값을 받아와야한다.)
        LoanHistory loanHistory = this.makeHistoryEntity(targetBook, new LoanHistory());

        loanHistoryRepository.save(loanHistory);
        return bookRepository.save(targetBook);
    }

    /**
     * 변경: Book -> LoanHistory
     */
    public static LoanHistory makeHistoryEntity(Book book, LoanHistory history) {
        Member member = CommonUtils.getMember();
        String now = CommonUtils.getNow();

        history.setBook(book);
        history.setLoanReceiver(book.getLoanReceiver());
        history.setLoanPhone(book.getLoanPhone());
        history.setLoanable(book.isLoanable());
        history.setLoanReason(book.getLoanReason());
        history.setLoanManager(member.getMemberId());
        history.setLoanDate(now);
        return history;
    }

    /**
     * 변경: Dto -> Entity(Book)
     */
    public static Book toEntity(Book book, LoanDto loanDto) {
        book.setLoanReceiver(loanDto.getLoanReceiver());
        book.setLoanPhone(loanDto.getLoanPhone());
        book.setLoanStart(loanDto.getLoanStart());
        book.setLoanEnd(loanDto.getLoanEnd());
        book.setLoanReason(loanDto.getLoanReason());
        return book;
    }
}
