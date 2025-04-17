package com.glory.chatapp.domain.Notice;

import com.glory.chatapp.util.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String administratorName;

    private boolean fixedTop;

    public Notice(String title, String content, String administratorName) {
        this.title = title;
        this.content = content;
        this.administratorName = administratorName;
    }

    public static Notice of(String title, String content, String administratorName) {
        return new Notice(title, content, administratorName);
    }
}
