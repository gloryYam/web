package com.glory.chatapp.domain.member.entity;

import com.glory.chatapp.util.BaseEntity;
import com.glory.chatapp.oauth.OauthUser;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.EnumType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    @Enumerated(STRING)
    private Role role;

    @OneToOne(mappedBy = "member")
    private OauthUser oauthUser;

    public Member(String username, String email, String password, Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public static Member of(String username, String email, String encodePassword) {
        return new Member(username, email, encodePassword, Role.USER);
    }
}
