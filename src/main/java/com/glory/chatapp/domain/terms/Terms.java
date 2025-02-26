package com.glory.chatapp.domain.terms;

import com.glory.chatapp.util.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Terms extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private Type type;

    public Terms(Long id, String title, String description, Type type) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.type = type;
    }
}
