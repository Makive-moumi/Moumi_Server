package com.makive.moumi.repository;

import com.makive.moumi.model.domain.Translation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranslationRepository extends JpaRepository<Translation, Long> {
    Slice<Translation> findAll(Specification<Translation> spec, Pageable pageable);

    int count(Specification<Translation> spec);
}