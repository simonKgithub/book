package com.manage.book.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    final private MemberRepository memberRepository;
    final private PasswordEncoder passwordEncoder;

    /**
     * 사용자 등록
     * @param memberDto
     * @return
     */
    public Member register(MemberDto memberDto) {
        Optional<Member> byId = memberRepository.findById(memberDto.getMemberId());
        if (byId.isPresent()) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
        Member member = Member.createMember(memberDto, passwordEncoder);

        return memberRepository.save(member);
    }
    /**
     * 스프링 시큐리티 - 로그인
     * @param userId
     * @return UserDetails (Member 에서 UserDetails 구현)
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        return memberRepository.findById(userId)
                .orElseThrow( () -> new UsernameNotFoundException("회원 정보가 없습니다."));
    }
}
