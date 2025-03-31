package com.glory.chatapp.repository.userTemrs;

import com.glory.chatapp.domain.userTerms.UserTerms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTermsRepository extends JpaRepository<UserTerms, Long> {

}
