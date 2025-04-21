package com.glory.chatapp.service.notice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glory.chatapp.IntegrationTestSupport;
import com.glory.chatapp.domain.Notice.Notice;
import com.glory.chatapp.repository.notice.NoticeRepository;
import com.glory.chatapp.service.notice.request.NoticeServiceRequest;
import com.glory.chatapp.service.notice.response.NoticeResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;



class NoticeServiceTest extends IntegrationTestSupport {

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private NoticeRepository noticeRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        noticeRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("공지사항 작성 성공 테스트")
    void write() {

        NoticeServiceRequest request = new NoticeServiceRequest("test 제목11", "test 내용11", "운영자11", true);

        NoticeResponse response = noticeService.write(request);

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getTitle()).isEqualTo(request.getTitle());
    }
}