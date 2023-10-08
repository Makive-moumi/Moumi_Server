package com.makive.moumi.model.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Long id;

    @ElementCollection(fetch = LAZY)
    private List<String> requestPdfs;

    @ElementCollection(fetch = LAZY)
    private List<String> responsePdfs;

    @CreationTimestamp
    private LocalDateTime requestDate;

    @Enumerated(STRING)
    private RequestStatus status;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "translation_id")
    private Translation translation;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToOne(mappedBy = "request", fetch = LAZY)
    private Review review;

    @PrePersist
    private void prePersist() {
        if (this.status == null) {
            this.status = RequestStatus.예약중;
        }
    }

    public void accept() {
        this.status = RequestStatus.진행중;
    }

    public void complete() {
        this.status = RequestStatus.완료;
    }

    public void uploadResponsePdfs(List<String> responsePdfs) {
        this.responsePdfs = responsePdfs;
    }
}
