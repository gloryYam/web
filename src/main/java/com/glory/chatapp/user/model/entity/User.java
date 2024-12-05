package com.glory.chatapp.user.model.entity;

import com.glory.chatapp.util.BaseEntity;
import com.glory.chatapp.oauth.OauthUser;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.EnumType.*;

@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

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

    @OneToOne(mappedBy = "user")
    private OauthUser oauthUser;

    public User(String username, String email, String password, Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public static User of(String username, String email, String encodePassword) {
        return new User(username, email, encodePassword, Role.USER);
    }
}
