package com.glory.chatapp.service.security;

import com.glory.chatapp.config.security.UserPrincipal;
import com.glory.chatapp.domain.member.Member;
import com.glory.chatapp.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public CustomUserDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = memberRepository.findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("해당 유저를 찾을 수 없습니다.."));

        log.info("member check item  :  {}", member);

        List<GrantedAuthority> roles = List.of(
                new SimpleGrantedAuthority("ROLE_" + member.getRole())
        );

        return new UserPrincipal(member, roles);
    }
}
