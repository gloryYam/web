package com.glory.chatapp.repository.notice;

import com.glory.chatapp.domain.Notice.Notice;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NoticeRepositoryTest {

    @Autowired
    private NoticeRepository noticeRepository;

    @BeforeEach
    void setUp() {
        noticeRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("NoticeRepository 테스트")
    void noticeRepository() {

        Notice notice = new Notice("test 제목", "test 내용", "운영자");

        noticeRepository.save(notice);

        Assertions.assertThat(noticeRepository.findAll()).hasSize(1);
        Assertions.assertThat(noticeRepository.findAll().get(0)).isEqualTo(notice);
    }
}