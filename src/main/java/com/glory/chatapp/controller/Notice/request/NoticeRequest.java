package com.glory.chatapp.controller.Notice.request;

import com.glory.chatapp.service.notice.request.NoticeServiceRequest;
import lombok.Getter;

@Getter
public class NoticeRequest {

    private String title;

    private String content;

    private String administratorName;

    private boolean fixedTop;

    public NoticeRequest(String title, String content, String administratorName, boolean fixedTop) {
        this.title = title;
        this.content = content;
        this.administratorName = administratorName;
        this.fixedTop = fixedTop;
    }

    public NoticeServiceRequest toNoticeServiceRequest() {
        return new NoticeServiceRequest(title, content, administratorName, fixedTop);
    }
}
