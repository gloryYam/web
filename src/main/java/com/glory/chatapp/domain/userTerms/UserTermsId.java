package com.glory.chatapp.domain.userTerms;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
public class UserTermsId implements Serializable {

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "term_id")
    private Long termsId;

    public UserTermsId(Long memberId, Long termsId) {
        this.memberId = memberId;
        this.termsId = termsId;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!(obj instanceof UserTermsId)) return false;
        UserTermsId other = (UserTermsId) obj;
        return Objects.equals(memberId, other.memberId) && Objects.equals(termsId, other.termsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId, termsId);
    }
}
