package com.glory.chatapp.domain.userTerms;


import com.glory.chatapp.domain.member.Member;
import com.glory.chatapp.domain.terms.Terms;
import com.glory.chatapp.util.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "UserTerms")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserTerms extends BaseEntity {

    @EmbeddedId
    private UserTermsId userTermsId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("memberId")
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("termsId")
    @JoinColumn(name = "terms_id", nullable = false)
    private Terms terms;

    @Column(name = "agreed_at", nullable = false)
    private LocalDateTime agreedAt;

    public UserTerms(UserTermsId userTermsId, Member member, Terms terms, LocalDateTime agreedAt) {
        this.userTermsId = userTermsId;
        this.member = member;
        this.terms = terms;
        this.agreedAt = agreedAt;
    }
}
