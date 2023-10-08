package com.makive.moumi.repository;

import com.makive.moumi.model.domain.Review;
import com.makive.moumi.model.dto.ReviewDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("SELECT NEW com.makive.moumi.model.dto.ReviewDTO(r.request.client.name, r.rating, r.content, CAST(r.reviewDate as LocalDate)) " +
            "FROM Review r " +
            "WHERE r.request.translation.id = :translationId")
    Slice<ReviewDTO> findAllByTranslationId(@Param("translationId") Long translationId, Pageable pageable);
}
