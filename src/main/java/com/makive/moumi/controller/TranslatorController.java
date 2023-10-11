package com.makive.moumi.controller;

import com.makive.moumi.exception.dto.DataResponse;
import com.makive.moumi.service.TranslatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/translators")
@RequiredArgsConstructor
public class TranslatorController {
    private final TranslatorService translatorService;

    @GetMapping("/{translatorId}")
    public DataResponse getTranslator(@PathVariable Long translatorId) {
        return DataResponse.of(translatorService.getTranslator(translatorId));
    }
}
