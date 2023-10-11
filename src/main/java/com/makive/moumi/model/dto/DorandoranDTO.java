package com.makive.moumi.model.dto;

import com.makive.moumi.model.domain.Dorandoran;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DorandoranDTO {
    private Long id;
    private String title;
    private List<String> category;
    private List<String> images;
    private LocalDate uploadDate;

    public static DorandoranDTO fromDorandoran(Dorandoran dorandoran) {
        return DorandoranDTO.builder()
                .id(dorandoran.getId())
                .title(dorandoran.getTitle())
                .category(dorandoran.getDorandoranCategories().stream()
                        .map(dorandoranCategory -> dorandoranCategory.getCategory().getName())
                        .collect(Collectors.toList()))
                .images(dorandoran.getImages())
                .uploadDate(dorandoran.getUploadDate())
                .build();
    }
}
