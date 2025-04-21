package com.glory.chatapp.service.notice;

import com.glory.chatapp.domain.Notice.Notice;
import com.glory.chatapp.repository.notice.NoticeRepository;
import com.glory.chatapp.service.notice.request.NoticeServiceRequest;
import com.glory.chatapp.service.notice.response.NoticeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.glory.chatapp.service.notice.response.NoticeResponse.*;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public NoticeResponse write(NoticeServiceRequest request) {

        Notice notice = Notice.of(request.getTitle(), request.getContent(), request.getAdministratorName());
        Notice savedNotice = noticeRepository.save(notice);

        return NoticeResponse.of(savedNotice);
    }
}
