package com.makive.moumi.model.dto.response;

import com.makive.moumi.model.dto.RequestDTO;
import com.makive.moumi.model.dto.ReviewDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestClientResponse {
    private RequestDTO request;
    private double reviewRating;
    private int reviewCount;
    private ReviewDTO review;
    private List<String> requestPdfs;
    private List<String> responsePdfs;
}
