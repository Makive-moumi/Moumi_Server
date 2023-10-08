package com.makive.moumi.model.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class DorandoranCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dorandoran_category_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "dorandoran_id")
    private Dorandoran dorandoran;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
