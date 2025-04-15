package com.glory.chatapp.service.Auth;

import com.glory.chatapp.domain.member.Member;
import com.glory.chatapp.exception._404.UserNotFoundException;
import com.glory.chatapp.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public void updateLastLoginTime(String username) {
        Member member = memberRepository.findByUsername(username)
                        .orElseThrow(UserNotFoundException::new);

        member.updateLastLoginTime();
    }
}
