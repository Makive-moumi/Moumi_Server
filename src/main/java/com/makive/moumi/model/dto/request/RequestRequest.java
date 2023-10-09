package com.makive.moumi.model.dto.request;

import lombok.Getter;

import java.util.List;

@Getter
public class RequestRequest {
    private List<String> category;
    private boolean hasReview;
}
