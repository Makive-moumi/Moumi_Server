package com.makive.moumi.model.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category", fetch = LAZY)
    private List<DorandoranCategory> dorandoranCategories = new ArrayList<>();

    @OneToMany(mappedBy = "category", fetch = LAZY)
    private List<TranslationCategory> translationCategories = new ArrayList<>();
}