package com.manage.book.member;

import com.manage.book.constant.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    @DisplayName("회원가입 중복 테스트")
    void duplicationTest(){
        MemberDto member1 = this.makeUserDto();
        MemberDto member2 = this.makeUserDto();

        memberService.register(member1);

        assertThrows(IllegalStateException.class, () -> memberService.register(member2));
    }

    @Test
    @DisplayName("회원가입 테스트")
    void registerTest(){
        MemberDto memberDto = this.makeUserDto();

        Member saved = memberService.register(memberDto);

        assertEquals(saved.getMemberId(), memberDto.getMemberId());
        //assertEquals(saved.getMemberPw(), memberDto.getMemberPw());
        assertEquals(saved.getMemberNm(), memberDto.getMemberNm());
        assertEquals(saved.getRole(), Role.ADMIN);
    }

    private MemberDto makeUserDto(){
        MemberDto memberDto = new MemberDto();
        memberDto.setMemberId("testId");
        memberDto.setMemberPw("testPw");
        memberDto.setMemberNm("testNm");
        return memberDto;
    }

}