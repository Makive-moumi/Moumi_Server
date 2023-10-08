package com.makive.moumi.model.domain;

import jakarta.persistence.*;
import lombok.Getter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
public class Translator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "translator_id")
    private Long id;

    private String email;

    private String password;

    private String name;

    private String specialty;   // 특화

    private String contactableTime; // 연락 가능 시간

    private String averageResponseTime; // 평균 응답 시간

    private String introduction;    // 소개

    @OneToOne(mappedBy = "translator", fetch = LAZY)
    private Translation translation;
}
