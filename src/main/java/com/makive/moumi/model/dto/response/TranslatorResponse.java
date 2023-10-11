package com.makive.moumi.model.dto.response;

import com.makive.moumi.model.dto.TranslatorDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TranslatorResponse {
    private TranslatorDTO translator;
}
