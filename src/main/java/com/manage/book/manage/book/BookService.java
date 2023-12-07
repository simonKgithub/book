package com.manage.book.manage.book;

import com.manage.book.manage.loan.LoanHistoryRepository;
import com.manage.book.member.Member;
import com.manage.book.utils.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {

    final private BookRepository bookRepository;
    final private LoanHistoryRepository loanHistoryRepository;

    /**
     * 등록: 도서
     * @param bookDto
     * @return
     */
    public Book addBook(BookDto bookDto) {
        Member member = CommonUtils.getMember();
        String now = CommonUtils.getNow();

        Book newBook = new Book();
        Book book = this.toEntity(newBook, bookDto);
        book.setRegistrant(member.getMemberId());
        book.setRegDate(now);

        validation(book);
        return bookRepository.save(book);
    }
    /**
     * 조회: 도서 다건 (페이징 처리-1)
     * @return
     */
    public Page<Book> findBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }
    /**
     * 조회: 도서 다건 (페이징 처리-2)
     * @return
     */
    public Page<Book> findBooks(String bookNm, String bookAuthor, Pageable pageable) {
        return bookRepository.findByBookNmContainingAndBookAuthorContaining(bookNm, bookAuthor, pageable);
    }
    /**
     * 조회: 도서 단건
     * @param bookIsbn
     * @return
     */
    public Book findBook(String bookIsbn) {
        return bookRepository.findByBookIsbn(bookIsbn);
    }

    /**
     * 삭제: 도서
     * @param bookDto
     */
    public void deleteBook(BookDto bookDto) {
        Book targetBook = this.findBook(bookDto.getBookIsbn());
        // 관련 이력 삭제
        loanHistoryRepository.deleteByBook(targetBook);
        bookRepository.delete(targetBook);
    }
    /**
     * 수정: 도서
     * @param bookDto
     * @return
     */
    public Book editBook(BookDto bookDto) {
        Member member = CommonUtils.getMember();
        String now = CommonUtils.getNow();
        Book savedBook = bookRepository.findByBookIsbn(bookDto.getBookIsbn());

        Book book = this.toEntity(savedBook, bookDto);
        book.setModifier(member.getMemberId());
        book.setModDate(now);

        return bookRepository.save(book);
    }
    /**
     * 조회: PK(ISBN) 중복 확인 메서드
     * @param book
     */
    private void validation(Book book) {
        Book byIsbn = bookRepository.findByBookIsbn(book.getBookIsbn());
        if (byIsbn != null) {
            throw new IllegalStateException("이미 등록된 ISBN 입니다.");
        }
    }
    /**
     * 변경: Dto -> Entity (등록자/일, 수정자/일 제외)
     * @param bookDto
     * @return
     */
    private static Book toEntity(Book book, BookDto bookDto) {
        book.setBookIsbn(bookDto.getBookIsbn());
        book.setBookNm(bookDto.getBookNm());
        book.setBookAuthor(bookDto.getBookAuthor());
        book.setBookPublisher(bookDto.getBookPublisher());
        book.setLoanable(true);
        return book;
    }
}
