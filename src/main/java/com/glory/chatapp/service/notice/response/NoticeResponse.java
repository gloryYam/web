package com.glory.chatapp.service.notice.response;

import lombok.Builder;

public class NoticeResponse {

    private String title;

    private String content;

    private String administratorName;

    private boolean fixedTop;

    @Builder
    public NoticeResponse(String title, String content, String administratorName, boolean fixedTop) {
        this.title = title;
        this.content = content;
        this.administratorName = administratorName;
        this.fixedTop = fixedTop;
    }
}
