package com.glory.chatapp.domain.member;

import com.glory.chatapp.oauth.OauthUser;
import com.glory.chatapp.util.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static jakarta.persistence.EnumType.STRING;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    // 약관 동의 여부 (회원가입 시 약관 동의를 받았는지 확인)
    @Column(nullable = false)
    private boolean termsAgreed;

    // 이메일 인증 여부 (추후 이메일 인증 로직 구현 시 활용)
    private boolean emailVerified = false;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RegistrationType registrationType;

    @Enumerated(STRING)
    private Role role;

    @OneToOne(mappedBy = "member")
    private OauthUser oauthUser;

    public Member(String email, String username, String password, boolean termsAgreed, boolean emailVerified, RegistrationType registrationType, Role role) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.termsAgreed = termsAgreed;
        this.emailVerified = emailVerified;
        this.registrationType = registrationType;
        this.role = role;
    }

    public static Member of(String email, String username, String encodePassword, boolean termsAgreed, boolean emailVerified, RegistrationType registrationType, Role role) {
        return new Member(email, username, encodePassword, termsAgreed, emailVerified, registrationType, role);
    }
}
