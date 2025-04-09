package com.glory.chatapp.service.security;

import com.glory.chatapp.config.security.UserPrincipal;
import com.glory.chatapp.domain.member.Member;
import com.glory.chatapp.repository.MemberRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;


public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public CustomUserDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = memberRepository.findByUsername(username);

        if(userNotFound(username, member)) {
            throw new UsernameNotFoundException(username);
        }

        List<GrantedAuthority> roles = List.of(
                new SimpleGrantedAuthority("ROLE_" + member.getRole())
        );

        return new UserPrincipal(member, roles);
    }

    private static boolean userNotFound(String username, Member member) {
        return !member.getUsername().equals(username);
    }
}
