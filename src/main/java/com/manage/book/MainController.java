package com.manage.book;

import com.manage.book.manage.book.Book;
import com.manage.book.manage.book.BookService;
import com.manage.book.manage.book.SearchForm;
import com.manage.book.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final BookService bookService;

    @GetMapping({"/main", "/"})
    public String main(@AuthenticationPrincipal Member member, Model model,
                       @ModelAttribute("searchForm") SearchForm searchForm,
                       @PageableDefault(size = 5) Pageable pageable){
        model.addAttribute("member", member);
        System.out.println("배포 테스트");
        Page<Book> bookDtoList;
        //페이징 처리
        if (searchForm != null &&
                (searchForm.getBookNm() != null || searchForm.getBookAuthor() != null)) {
            bookDtoList = bookService.findBooks(searchForm.getBookNm(), searchForm.getBookAuthor(), pageable);
        } else {
            bookDtoList = bookService.findBooks(pageable);
        }
        model.addAttribute("bookDtoList", bookDtoList);
        return "main";
    }
}
