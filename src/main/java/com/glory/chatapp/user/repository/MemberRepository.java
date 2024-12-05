package com.glory.chatapp.user.repository;

import com.glory.chatapp.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
