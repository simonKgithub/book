package com.manage.book.utils;

import com.manage.book.member.Member;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommonUtils {

    /**
     * 현재 로그인(인증된) 사용자를 반환한다.
     * @return Member
     */
    public static Member getMember(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            return (Member)authentication.getPrincipal();
        } else {
            return null;
        }
    }

    /**
     * 현재 시간을 년월일시분초(yyyyMMddHHmmss) 형식으로 반환한다.
     * @return String
     */
    public static String getNow(){
        LocalDateTime now = LocalDateTime.now();
        return now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }
}
