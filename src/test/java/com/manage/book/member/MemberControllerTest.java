package com.manage.book.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class MemberControllerTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    PasswordEncoder passwordEncoder;

//    @Test
//    @DisplayName("비밀번호 실패 테스트")
    void loginPwTest() throws Exception {
        Member member = createMember();

        mockMvc.perform(formLogin()
                .userParameter("memberId")
                .loginProcessingUrl("/login")
                .user(member.getMemberId())
        ).andExpect(SecurityMockMvcResultMatchers.unauthenticated());

    }

//    @Test
//    @DisplayName("로그인 성공 테스트")
//    @WithMockUser
    void loginSuccessTest() throws Exception {
        Member member = createMember();

        mockMvc.perform(formLogin()
                .userParameter("memberId")
                .loginProcessingUrl("/login")
                .user(member.getMemberId())
                .password(member.getMemberPw())
        ).andExpect(SecurityMockMvcResultMatchers.authenticated());
    }

    public Member createMember(){
        MemberDto memberDto = new MemberDto();
        memberDto.setMemberId("testId");
        memberDto.setMemberPw("testPw");
        memberDto.setMemberNm("testNm");
        return memberService.register(memberDto);
    }

}