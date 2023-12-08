package com.manage.book.member;

import com.manage.book.constant.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity @Table(name = "member")
@Getter @Setter @ToString
public class Member implements UserDetails {
    @Id @Column(name = "member_id", length = 20)
    private String memberId;

    @Column(name = "member_pw", length = 255, nullable = false)
    private String memberPw;

    @Column(name = "member_nm", length = 20, nullable = false)
    private String memberNm;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member createMember(MemberDto memberDto, PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setMemberId(memberDto.getMemberId());
        member.setMemberPw(passwordEncoder.encode(memberDto.getMemberPw()));
        member.setMemberNm(memberDto.getMemberNm());
        member.setRole(Role.ADMIN);
        return member;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(getRole().getAuthority()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return getMemberPw();
    }

    @Override
    public String getUsername() {
        return getMemberId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
