package com.glory.chatapp.model.entity.user;

import com.glory.chatapp.model.entity.BaseEntity;
import com.glory.chatapp.model.entity.oauth.OauthUser;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    private Role role;

    @OneToOne(mappedBy = "user")
    private OauthUser oauthUser;

}
