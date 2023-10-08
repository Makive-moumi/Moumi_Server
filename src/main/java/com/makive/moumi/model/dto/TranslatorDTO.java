package com.makive.moumi.model.dto;

import com.makive.moumi.model.domain.Translator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TranslatorDTO {
    private String name;
    private String introduction;
    private String specialty;
    private String contactableTime;
    private String averageResponseTime;

    public static TranslatorDTO fromTranslator(Translator translator) {
        return TranslatorDTO.builder()
                .name(translator.getName())
                .introduction(translator.getIntroduction())
                .specialty(translator.getSpecialty())
                .contactableTime(translator.getContactableTime())
                .averageResponseTime(translator.getAverageResponseTime())
                .build();
    }
}
