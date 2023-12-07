package com.manage.book.manage;

import com.manage.book.manage.book.Book;
import com.manage.book.manage.book.BookDto;
import com.manage.book.manage.book.BookService;
import com.manage.book.manage.book.SearchForm;
import com.manage.book.manage.loan.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/manage")
@RequiredArgsConstructor
@Slf4j
public class ManageController {

    final private BookService bookService;
    final private LoanService loanService;
    final private LoanHistoryService loanHistoryService;

    /**
     * 대출 이력 확인하기
     * @param bookIsbn
     * @param model
     * @return
     */
    @GetMapping("/loanHistory/{bookIsbn}")
    public String loanHistory(@PathVariable String bookIsbn, Model model) {
        Book book = bookService.findBook(bookIsbn);
        List<LoanHistory> loanHistoryList = loanHistoryService.findHistories(book);
        List<LoanHistoryDto> loanHistoryDtoList = new ArrayList<>();
        loanHistoryList.forEach(entity -> {
            loanHistoryDtoList.add(LoanHistoryDto.of(entity));
        });
        model.addAttribute("loanHistoryDtoList", loanHistoryDtoList);
        return "manage/loanHistoryPopup";
    }

    /**
     * 도서 반납
     * @param loanDto
     * @return ResponseEntity<String>
     */
    @PostMapping("/returnBook")
    @ResponseBody
    public ResponseEntity<String> returnBook(@RequestBody LoanDto loanDto){
        loanService.returnBook(loanDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 도서 대출
     * @param loanDto
     * @param bindingResult
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/loan")
    public String loanBook(@Valid LoanDto loanDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "manage/loanBookPopup"; //화면 직접 지정
        }
        //도서대출
        loanService.loanBook(loanDto);

        redirectAttributes.addAttribute("bookIsbn", loanDto.getBookIsbn());
        return "redirect:/manage/loan/{bookIsbn}?loan=success";
    }

    /**
     * 화면 이동: 도서 대출
     */
    @GetMapping("/loan/{bookIsbn}")
    public String loanBook(@PathVariable String bookIsbn, Model model) {
        Book book = bookService.findBook(bookIsbn);
        model.addAttribute("loanDto", LoanDto.of(book));
        return "manage/loanBookPopup";
    }

    /**
     * 삭제: 도서 삭제
     * @param bookDto
     * @return
     */
    @PostMapping("/deleteBook")
    @ResponseBody
    public ResponseEntity<String> deleteBook(@RequestBody BookDto bookDto) {
        bookService.deleteBook(bookDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 수정: 도서 수정
     * @param bookDto
     * @param bindingResult
     * @param model
     * @return
     */
    @PostMapping("/editBook")
    public String editBook(@Valid BookDto bookDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "manage/editBook"; //화면 직접 지정
        }
        bookService.editBook(bookDto);

        return "redirect:/manage/books?edit=success";
    }

    /**
     * 화면 이동: 도서 수정
     */
    @GetMapping("/editBook/{bookIsbn}")
    public String editBook(@PathVariable String bookIsbn, Model model){
        Book book = bookService.findBook(bookIsbn);
        model.addAttribute("bookDto", BookDto.of(book));
        return "manage/editBook";
    }

    /**
     * 도서 등록
     */
    @PostMapping("/addBook")
    public String addBook(@Valid BookDto bookDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "manage/addBook";
        }
        try {
            bookService.addBook(bookDto);
            return "redirect:/manage/books?add=success";
        } catch (IllegalStateException e) {
            model.addAttribute("error", e.getMessage());
            return "manage/addBook";
        }
    }

    /**
     * 화면 이동: 도서 등록 페이지
     * @return
     */
    @GetMapping("/addBook")
    public String addBook(Model model){
        model.addAttribute("bookDto", new BookDto());
        return "manage/addBook";
    }

    /***
     * 화면 이동: 도서 관리 페이지
     * @return
     */
    @GetMapping("/books")
    public String books(Model model,
                        @ModelAttribute("searchForm") SearchForm searchForm,
                        @PageableDefault(size = 5) Pageable pageable){
        Page<Book> bookDtoList;
        //페이징 처리
        if (searchForm != null &&
                (searchForm.getBookNm() != null || searchForm.getBookAuthor() != null)) {
            bookDtoList = bookService.findBooks(searchForm.getBookNm(), searchForm.getBookAuthor(), pageable);
        } else {
            bookDtoList = bookService.findBooks(pageable);
        }
        model.addAttribute("bookDtoList", bookDtoList);
        return "manage/books";
    }
}
