package com.makive.moumi.controller;

import com.makive.moumi.exception.dto.DataResponse;
import com.makive.moumi.model.dto.request.DorandoranRequest;
import com.makive.moumi.service.DorandoranService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dorandoran")
@RequiredArgsConstructor
public class DorandoranController {
    private final DorandoranService dorandoranService;

    @GetMapping
    public DataResponse getDorandorans(@RequestBody DorandoranRequest dorandoranRequest, @PageableDefault(sort = {"uploadDate"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return DataResponse.of(dorandoranService.getDorandorans(dorandoranRequest, pageable));
    }

    @GetMapping("/{dorandoranId}")
    public DataResponse getDorandoran(@PathVariable Long dorandoranId) {
        return DataResponse.of(dorandoranService.getDorandoran(dorandoranId));
    }
}
