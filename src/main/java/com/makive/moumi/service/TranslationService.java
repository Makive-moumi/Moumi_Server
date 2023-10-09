package com.makive.moumi.service;

import com.makive.moumi.exception.Code;
import com.makive.moumi.exception.GeneralException;
import com.makive.moumi.model.domain.Translation;
import com.makive.moumi.model.dto.TranslationDTO;
import com.makive.moumi.model.dto.TranslatorDTO;
import com.makive.moumi.model.dto.request.TranslationRequest;
import com.makive.moumi.model.dto.response.TranslationResponse;
import com.makive.moumi.model.dto.response.TranslationsCountResponse;
import com.makive.moumi.model.dto.response.TranslationsResponse;
import com.makive.moumi.repository.TranslationRepository;
import com.makive.moumi.repository.specification.TranslationSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TranslationService {
    private final TranslationRepository translationRepository;

    public TranslationsResponse getTranslations(TranslationRequest translationRequest, Pageable pageable) {
        List<String> category = translationRequest.getCategory();
        int minPrice = translationRequest.getMinPrice();
        int maxPrice = translationRequest.getMaxPrice();
        Specification<Translation> spec = TranslationSpecifications.findAllByCategoryAndPriceRange(category, minPrice, maxPrice);

        Slice<Translation> translationSlice = translationRepository.findAll(spec, pageable);
        List<TranslationDTO> translationDTOList = translationSlice.getContent().stream()
                .map(TranslationDTO::fromTranslation)
                .collect(Collectors.toList());

        return TranslationsResponse.builder()
                .content(translationDTOList)
                .first(translationSlice.isFirst())
                .last(translationSlice.isLast())
                .build();
    }

    public TranslationsCountResponse countTranslations(TranslationRequest translationRequest) {
        List<String> category = translationRequest.getCategory();
        int minPrice = translationRequest.getMinPrice();
        int maxPrice = translationRequest.getMaxPrice();
        Specification<Translation> spec = TranslationSpecifications.findAllByCategoryAndPriceRange(category, minPrice, maxPrice);

        return TranslationsCountResponse.builder()
                .count(translationRepository.count(spec))
                .build();
    }

    public TranslationResponse getTranslation(Long translationId) {
        Translation translation = translationRepository.findById(translationId)
                .orElseThrow(() -> new GeneralException(Code.DATA_NOT_FOUND));
        TranslationDTO translationDTO = TranslationDTO.fromTranslation(translation);
        TranslatorDTO translatorDTO = TranslatorDTO.fromTranslator(translation.getTranslator());

        return TranslationResponse.builder()
                .translation(translationDTO)
                .translator(translatorDTO)
                .build();
    }
}
