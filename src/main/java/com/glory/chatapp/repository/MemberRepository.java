package com.glory.chatapp.repository;

import com.glory.chatapp.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByMemberId(String email);
}
