package com.glory.chatapp.controller.Notice;

import com.glory.chatapp.controller.ApiResponse;
import com.glory.chatapp.controller.Notice.request.NoticeRequest;
import com.glory.chatapp.service.notice.NoticeService;
import com.glory.chatapp.service.notice.response.NoticeResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticeController {

    private final NoticeService noticeService;

    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    // 작성
    @PostMapping("/potato/notice")
    public ApiResponse<NoticeResponse> write(NoticeRequest request) {
        return noticeService.write(request.toNoticeServiceRequest());
    }
}
