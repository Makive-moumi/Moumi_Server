package com.makive.moumi.model.dto.request;

import lombok.Getter;

import java.util.List;

@Getter
public class TranslationRequest {
    private Integer minPrice;
    private Integer maxPrice;
    private List<String> category;
}
