package com.makive.moumi.model.dto;

import com.makive.moumi.model.domain.Request;
import com.makive.moumi.model.domain.RequestStatus;
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
public class RequestDTO {
    private Long id;
    private String title;
    private List<String> category;
    private String image;
    private RequestStatus status;
    public static RequestDTO fromRequest(Request request) {
        return RequestDTO.builder()
                .id(request.getId())
                .title(request.getTranslation().getTitle())
                .category(request.getTranslation().getTranslationCategories().stream()
                        .map(translationCategory -> translationCategory.getCategory().getName())
                        .collect(Collectors.toList()))
                .image(request.getTranslation().getImage())
                .status(request.getStatus())
                .build();
    }
}
