package com.glory.chatapp.domain.terms;

import com.glory.chatapp.domain.userTerms.UserTerms;
import com.glory.chatapp.util.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 약관에 대한 정보를 저장하는 엔티티
 */
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

    @OneToMany(mappedBy = "terms")
    private List<UserTerms> userTerms = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "current_version_id", referencedColumnName = "id")
    private TermVersions currentVersion;

    @OneToMany(mappedBy = "terms")
    private List<TermVersions> termVersions = new ArrayList<>();

    public Terms(Long id, String title, String description, Type type) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.type = type;
    }
}
