package com.glory.chatapp.domain.member;

import com.glory.chatapp.api.service.member.response.SignResponse;
import com.glory.chatapp.domain.userTerms.UserTerms;
import com.glory.chatapp.oauth.OauthUser;
import com.glory.chatapp.util.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

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
    private String memberId;

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


    public Member(String memberId, String username, String password, RegistrationType registrationType, Role role) {
        this.memberId = memberId;
        this.username = username;
        this.password = password;
        this.registrationType = registrationType;
        this.role = role;
    }

    public static Member of(String userId, String username, String encodePassword, RegistrationType registrationType, Role role) {
        return new Member(userId, username, encodePassword, registrationType, role);
    }

    public boolean isAmin() {
        return this.role == Role.ADMIN;
    }

    public SignResponse toSignResponse() {
        Role role = this.isAmin() ? Role.ADMIN : Role.USER;

        return SignResponse.builder()
                .email(this.memberId)
                .username(this.username)
                .role(role)
                .build();
    }

    public Long getMemberId() {
        return this.id;
    }
}
