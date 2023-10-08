package com.makive.moumi.model.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
public class Translation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "translation_id")
    private Long id;

    private String title;

    private int price;

    private String image;

    @CreationTimestamp
    private LocalDateTime registerDate;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "translator_id")
    private Translator translator;

    @OneToMany(mappedBy = "translation", cascade = ALL, fetch = LAZY)
    private List<Request> requests = new ArrayList<>();

    @OneToMany(mappedBy = "translation", fetch = LAZY)
    private List<TranslationCategory> translationCategories = new ArrayList<>();
}
