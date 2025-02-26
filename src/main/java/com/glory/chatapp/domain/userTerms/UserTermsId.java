package com.glory.chatapp.domain.userTerms;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
public class UserTermsId implements Serializable {

    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "term_id")
    private Long term_id;

    public UserTermsId(Long user_id, Long term_id) {
        this.user_id = user_id;
        this.term_id = term_id;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!(obj instanceof UserTermsId)) return false;
        UserTermsId other = (UserTermsId) obj;
        return Objects.equals(user_id, other.user_id) && Objects.equals(term_id, other.term_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, term_id);
    }
}
