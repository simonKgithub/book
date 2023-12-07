package com.manage.book.member;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    final private MemberService memberService;

    /**
     * 화면 이동: 로그인
     * @return
     */
    @GetMapping("/login")
    public String memberLogin(Model model){
        model.addAttribute("memberDto", new MemberDto());
        return "member/login";
    }

    /**
     * 회원 가입
     * @param memberDto
     * @param bindingResult
     * @param model
     * @return
     */
    @PostMapping("/join")
    public String memberJoin(@Valid MemberDto memberDto, BindingResult bindingResult, Model model) {
        /** 입력 규칙 미준수 */
        if (bindingResult.hasErrors()) {
            return "member/join";
        }
        try {
            memberService.register(memberDto);
            return "redirect:/member/login?success";
        } catch (IllegalStateException e) {
            /** 사용자 중복 */
            model.addAttribute("duplication", e.getMessage());
            return "member/join";
        }
    }

    /**
     * 화면 이동: 사용자 회원 가입
     * @return
     */
    @GetMapping("/join")
    public String memberJoin(Model model){
        model.addAttribute("memberDto", new MemberDto());
        return "member/join";
    }
}
