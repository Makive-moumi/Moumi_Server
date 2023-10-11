package com.makive.moumi.repository;

import com.makive.moumi.model.domain.Translator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranslatorRepository extends JpaRepository<Translator, Long> {
}
