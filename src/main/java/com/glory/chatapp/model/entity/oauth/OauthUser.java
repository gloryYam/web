package com.glory.chatapp.model.entity.oauth;

import com.glory.chatapp.model.entity.BaseEntity;
import com.glory.chatapp.model.entity.user.User;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class OauthUser extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String oauthProvider;
    private String oauthId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


}
