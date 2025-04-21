package com.glory.chatapp.service.notice.response;

import com.glory.chatapp.domain.Notice.Notice;
import lombok.Builder;
import lombok.Getter;

@Getter
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

    public static NoticeResponse of(Notice notice) {
        return NoticeResponse.builder()
                .title(notice.getTitle())
                .content(notice.getContent())
                .administratorName(notice.getAdministratorName())
                .fixedTop(notice.isFixedTop())
                .build();
    }
}
