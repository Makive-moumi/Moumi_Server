package com.makive.moumi.model.dto;

import com.makive.moumi.model.domain.Translation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TranslationDTO {
    private Long id;
    private String title;
    private int price;
    private double rating;
    private int count;
    private List<String> category;
    private String image;

    public static TranslationDTO fromTranslation(Translation translation) {
        List<Double> reviewRatings = translation.getRequests().stream()
                .map(request ->  request.getReview() != null ? request.getReview().getRating() : 0.0)
                .collect(Collectors.toList());

        double averageRating = calculateAverageRating(reviewRatings);
        int reviewCount = reviewRatings.size();

        return TranslationDTO.builder()
                .id(translation.getId())
                .title(translation.getTitle())
                .price(translation.getPrice())
                .rating(averageRating)
                .count(reviewCount)
                .category(translation.getTranslationCategories().stream()
                        .map(translationCategory -> translationCategory.getCategory().getName())
                        .collect(Collectors.toList()))
                .image(translation.getImage())
                .build();
    }

    private static double calculateAverageRating(List<Double> reviewRatings) {
        if (reviewRatings.isEmpty()) {
            return 0.0;
        }
        double sum = reviewRatings.stream().mapToDouble(Double::doubleValue).sum();
        return Double.parseDouble(String.format("%.1f", sum / reviewRatings.size()));
    }
}
