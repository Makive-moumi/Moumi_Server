package com.makive.moumi.controller;

import com.makive.moumi.exception.dto.DataResponse;
import com.makive.moumi.model.dto.request.TranslationRequest;
import com.makive.moumi.service.TranslationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import static org.springframework.data.domain.Sort.Direction.DESC;

@RestController
@RequestMapping("/translations")
@RequiredArgsConstructor
public class TranslationController {
    private final TranslationService translationService;

    @GetMapping()
    public DataResponse getTranslations(@RequestBody TranslationRequest translationRequest, @PageableDefault(sort = {"registerDate"}, direction = DESC) Pageable pageable) {
        return DataResponse.of(translationService.findTranslationsByRequest(translationRequest, pageable));
    }

    @GetMapping("/count")
    public DataResponse countTranslations(@RequestBody TranslationRequest translationRequest) {
        return DataResponse.of(translationService.countTranslationsByRequest(translationRequest));
    }

    @GetMapping("/{translation_id}")
    public DataResponse getTranslation(@PathVariable("translation_id") Long translationId) {
        return DataResponse.of(translationService.findTranslationById(translationId));
    }
}
