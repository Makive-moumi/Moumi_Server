package com.makive.moumi.service;

import com.makive.moumi.model.domain.Request;
import com.makive.moumi.model.domain.Review;
import com.makive.moumi.model.dto.ReviewDTO;
import com.makive.moumi.model.dto.request.ReviewRequest;
import com.makive.moumi.model.dto.response.ReviewResponse;
import com.makive.moumi.repository.RequestRepository;
import com.makive.moumi.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {
    private final RequestRepository requestRepository;
    private final ReviewRepository reviewRepository;

    public ReviewResponse getReviews(Long translationId, Pageable pageable) {
        Slice<ReviewDTO> reviews = reviewRepository.findAllByTranslationId(translationId, pageable);

        return ReviewResponse.builder()
                .content(reviews.getContent())
                .first(reviews.isFirst())
                .last(reviews.isLast())
                .build();
    }

    @Transactional
    public void addReview(Long requestId, ReviewRequest reviewRequest) {
        Request request = requestRepository.findById(requestId).orElse(null);

        Review review = Review.builder()
                .request(request)
                .rating(reviewRequest.getRating())
                .content(reviewRequest.getContent())
                .build();
        reviewRepository.save(review);
    }
}
