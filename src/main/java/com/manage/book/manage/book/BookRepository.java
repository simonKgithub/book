package com.manage.book.manage.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Override
    Page<Book> findAll(Pageable pageable);

    Page<Book> findByBookNmContainingAndBookAuthorContaining(String title, String author, Pageable pageable);

    Book findByBookIsbn(String bookIsbn);
}
