package com.makive.moumi.controller;

import com.makive.moumi.exception.Code;
import com.makive.moumi.exception.dto.DataResponse;
import com.makive.moumi.exception.dto.Response;
import com.makive.moumi.model.dto.request.RequestRequest;
import com.makive.moumi.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static org.springframework.data.domain.Sort.Direction.DESC;

@RestController
@RequiredArgsConstructor
public class RequestController {
    private final RequestService requestService;

    @PostMapping("/requests")
    public DataResponse getRequests(@RequestBody RequestRequest requestRequest, @PageableDefault(sort = {"requestDate"}, direction = DESC) Pageable pageable) {
        return DataResponse.of(requestService.getRequests(requestRequest, pageable));
    }

    @GetMapping("/requests/{requestId}/client")
    public DataResponse getRequestClient(@PathVariable Long requestId) {
        return DataResponse.of(requestService.getRequestClient(requestId));
    }

    @GetMapping("/requests/{requestId}/translator")
    public DataResponse getRequestTranslator(@PathVariable Long requestId) {
        return DataResponse.of(requestService.getRequestTranslator(requestId));
    }

    @PostMapping("/translations/{translationId}/request")
    public Response addRequest(@PathVariable Long translationId, @RequestParam("file") List<MultipartFile> files) throws IOException {
        requestService.addRequest(translationId, files);
        return Response.of(true, Code.OK);
    }

    @PatchMapping("/requests/{requestId}/accept")
    public Response acceptRequest(@PathVariable Long requestId) {
        requestService.acceptRequest(requestId);
        return Response.of(true, Code.OK);
    }

    @PatchMapping("/requests/{requestId}/complete")
    public Response completeRequest(@PathVariable Long requestId, @RequestParam("file") List<MultipartFile> files) throws IOException {
        requestService.completeRequest(requestId, files);
        return Response.of(true, Code.OK);
    }
}
