package com.makive.moumi.model.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
public class Dorandoran {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dorandoran_id")
    private Long id;

    private String title;

    @ElementCollection(fetch = LAZY)
    private List<String> images;

    @CreationTimestamp
    private LocalDate uploadDate;

    @OneToMany(mappedBy = "dorandoran")
    private List<DorandoranCategory> dorandoranCategories = new ArrayList<>();
}
