package com.glory.chatapp.service.notice.request;

public class NoticeServiceRequest {

    private String title;

    private String content;

    private String administratorName;

    private boolean fixedTop;

    public NoticeServiceRequest(String title, String content, String administratorName, boolean fixedTop) {
        this.title = title;
        this.content = content;
        this.administratorName = administratorName;
        this.fixedTop = fixedTop;
    }
}
