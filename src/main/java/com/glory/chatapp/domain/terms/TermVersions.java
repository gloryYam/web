package com.glory.chatapp.domain.terms;

import com.glory.chatapp.util.BaseEntity;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;


/**
 * 약관 버전관리 엔티티
 */
@Entity
@Table(name = "Term_Versions")
@NoArgsConstructor
public class TermVersions extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int version;

    @Column(nullable = false)
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "terms_id", nullable = false)
    private Terms terms;

}
