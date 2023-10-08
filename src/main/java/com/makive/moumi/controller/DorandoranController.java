package com.makive.moumi.controller;

import com.makive.moumi.exception.dto.DataResponse;
import com.makive.moumi.model.dto.request.DorandoranRequest;
import com.makive.moumi.service.DorandoranService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dorandoran")
@RequiredArgsConstructor
public class DorandoranController {
    private final DorandoranService dorandoranService;

    @GetMapping
    public DataResponse getDorandoran(@RequestBody DorandoranRequest dorandoranRequest, @PageableDefault(sort = {"uploadDate"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return DataResponse.of(dorandoranService.findDorandoranByCategoryNames(dorandoranRequest, pageable));
    }
}
