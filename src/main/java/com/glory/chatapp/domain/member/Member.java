package com.glory.chatapp.domain.member;

import com.glory.chatapp.api.service.Auth.response.SignResponse;
import com.glory.chatapp.domain.userTerms.UserTerms;
import com.glory.chatapp.oauth.OauthUser;
import com.glory.chatapp.util.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.EnumType.STRING;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
    private RegistrationType registrationType;

    @Enumerated(STRING)
    private Role role;

    @OneToOne(mappedBy = "member")
    private OauthUser oauthUser;

    @OneToMany(mappedBy = "member")
    private List<UserTerms> userTerms = new ArrayList<>();

    private LocalDateTime lastLoginTime;

    public Member(String userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }


    public static Member of(String userId, String username, String encodePassword) {
        Member member = new Member();
        member.userId = userId;
        member.username = username;
        member.password = encodePassword;
        member.role = Role.USER; // 기본값 설정해도 됨
        return member;
    }

    public boolean isAmin() {
        return this.role == Role.ADMIN;
    }

    public SignResponse toSignResponse() {
        Role role = this.isAmin() ? Role.ADMIN : Role.USER;

        return SignResponse.builder()
                .email(this.userId)
                .username(this.username)
                .role(role)
                .build();
    }

    public void updateLastLoginTime(LocalDateTime loginTime) {
        this.lastLoginTime = loginTime;
    }


    public Long getMemberId() {
        return this.id;
    }
}
