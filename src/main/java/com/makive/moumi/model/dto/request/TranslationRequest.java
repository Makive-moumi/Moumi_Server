package com.makive.moumi.model.dto.request;

import lombok.Getter;

import java.util.List;

@Getter
public class TranslationRequest {
    private int minPrice;
    private int maxPrice;
    private List<String> category;
}
