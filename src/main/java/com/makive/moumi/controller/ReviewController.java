package com.makive.moumi.controller;

import com.makive.moumi.exception.Code;
import com.makive.moumi.exception.dto.DataResponse;
import com.makive.moumi.exception.dto.Response;
import com.makive.moumi.model.dto.request.ReviewRequest;
import com.makive.moumi.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import static org.springframework.data.domain.Sort.Direction.DESC;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("translations/{translation_id}/reviews")
    public DataResponse<Object> getReviews(@PathVariable("translation_id") Long translationId, @PageableDefault(sort = {"reviewDate"}, direction = DESC) Pageable pageable) {
        return DataResponse.of(reviewService.getReviews(translationId, pageable));
    }

    @PostMapping("/requests/{request_id}/review")
    public Response addReview(@PathVariable("request_id") Long requestId, @RequestBody ReviewRequest reviewRequest) {
        reviewService.addReview(requestId, reviewRequest);
        return Response.of(true, Code.OK);
    }
}
