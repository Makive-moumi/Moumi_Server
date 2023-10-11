package com.makive.moumi.service;

import com.makive.moumi.exception.Code;
import com.makive.moumi.exception.GeneralException;
import com.makive.moumi.model.domain.Translator;
import com.makive.moumi.model.dto.TranslatorDTO;
import com.makive.moumi.model.dto.response.TranslatorResponse;
import com.makive.moumi.repository.TranslatorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TranslatorService {
    private final TranslatorRepository translatorRepository;

    public TranslatorResponse getTranslator(Long clientId) {
        Translator translator = translatorRepository.findById(clientId)
                .orElseThrow(() -> new GeneralException(Code.DATA_NOT_FOUND));

        return TranslatorResponse.builder()
                .translator(TranslatorDTO.fromTranslator(translator))
                .build();
    }
}
