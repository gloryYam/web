package com.glory.chatapp.config;

import com.glory.chatapp.config.security.UserPrincipal;
import com.glory.chatapp.domain.member.Member;
import com.glory.chatapp.domain.member.Role;
import com.glory.chatapp.repository.MemberRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.ArrayList;
import java.util.List;

public class TestMockSecurityContext implements WithSecurityContextFactory<TestMockMember> {

    private final MemberRepository memberRepository;

    public TestMockSecurityContext(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Override
    public SecurityContext createSecurityContext(TestMockMember annotation) {

        Member member = Member.of(annotation.nickname(), annotation.username(), annotation.password());

        memberRepository.save(member);

        List<SimpleGrantedAuthority> roles = List.of(new SimpleGrantedAuthority(Role.USER.getValue()));

        UserPrincipal userPrincipal = new UserPrincipal(member, roles);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userPrincipal, userPrincipal.getPassword(), roles);

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authenticationToken);

        return context;
    }
}
