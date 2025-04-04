package com.glory.chatapp.domain.userTerms;


import com.glory.chatapp.domain.member.Member;
import com.glory.chatapp.domain.terms.Terms;
import com.glory.chatapp.util.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 특정 사용자가 특정 약관에 동의했는지 여부를 저장하는 엔티티
 */
@Entity
@Table(name = "UserTerms")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserTerms extends BaseEntity {

    @EmbeddedId
    private UserTermsId userTermsId;

    @MapsId("memberId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @MapsId("termsId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "terms_id", nullable = false)
    private Terms terms;

    @Column(name = "agreed", nullable = false)
    private boolean agreed;

    @Column(name = "agreed_at", nullable = false)
    private LocalDateTime agreedAt;

    public UserTerms(UserTermsId userTermsId, Member member, Terms terms, boolean agreed, LocalDateTime agreedAt) {
        this.userTermsId = userTermsId;
        this.member = member;
        this.terms = terms;
        this.agreed = agreed;
        this.agreedAt = agreedAt;
    }
}
