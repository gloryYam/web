package com.glory.chatapp.repository;

import com.glory.chatapp.domain.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        memberRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("findByUserName 테스트")
    void findByUserName() {

        Member member = Member.of("glory2", "test2", "test123");
        memberRepository.save(member);

        Optional<Member> findMember = memberRepository.findByUsername("test2");

        Assertions.assertThat(findMember.get().getUsername()).isEqualTo("test2");
        Assertions.assertThat(findMember).isNotNull();
    }


    @Test
    @DisplayName("findByUserName 실패 테스트")
    void failToFindUserByUsername() {

        String invalidUsername = "not_exist_user";

        Optional<Member> member = memberRepository.findByUsername(invalidUsername);

        Assertions.assertThat(member).isEmpty();
    }

}