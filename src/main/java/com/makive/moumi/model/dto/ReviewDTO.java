package com.makive.moumi.model.dto;

import com.makive.moumi.model.domain.Client;
import com.makive.moumi.model.domain.Request;
import com.makive.moumi.model.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
    private String name;
    private double rating;
    private String content;
    private LocalDate reviewDate;

    public static ReviewDTO fromReview(Review review) {
        Request request = review.getRequest();
        Client client = request.getClient();

        return ReviewDTO.builder()
                .name(client.getName())
                .rating(review.getRating())
                .content(review.getContent())
                .reviewDate(review.getReviewDate().toLocalDate())
                .build();
    }
}