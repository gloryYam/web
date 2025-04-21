package com.glory.chatapp.domain.Notice;

import com.glory.chatapp.util.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Getter
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notice notice = (Notice) o;
        return fixedTop == notice.fixedTop && Objects.equals(id, notice.id) && Objects.equals(title, notice.title) && Objects.equals(content, notice.content) && Objects.equals(administratorName, notice.administratorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, administratorName, fixedTop);
    }
}
