package com.makive.moumi.model.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long id;

    private String email;

    private String password;

    private String name;

    @OneToMany(mappedBy = "client", cascade = ALL, fetch = LAZY)
    private List<Request> requests = new ArrayList<>();
}
