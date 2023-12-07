package com.manage.book.member;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class MemberDto {
    @NotEmpty(message = "아이디는 필수입니다.")
    private String memberId;
    @NotEmpty(message = "비밀번호는 필수입니다.")
    private String memberPw;
    @NotEmpty(message = "이름은 필수입니다.")
    private String memberNm;
}
