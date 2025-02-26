package com.glory.chatapp.oauth;

import com.glory.chatapp.util.BaseEntity;
import com.glory.chatapp.domain.member.Member;
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
    private Member member;


}
