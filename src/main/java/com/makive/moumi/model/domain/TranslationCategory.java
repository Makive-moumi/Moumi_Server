package com.makive.moumi.model.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class TranslationCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "translation_category_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "translation_id")
    private Translation translation;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
