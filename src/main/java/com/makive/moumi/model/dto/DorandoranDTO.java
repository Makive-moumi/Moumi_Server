package com.makive.moumi.model.dto;

import com.makive.moumi.model.domain.Dorandoran;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DorandoranDTO {
    private Long id;
    private String title;
    private List<String> images;
    private LocalDate uploadDate;

    public static DorandoranDTO fromDorandoran(Dorandoran dorandoran) {
        return DorandoranDTO.builder()
                .id(dorandoran.getId())
                .title(dorandoran.getTitle())
                .images(dorandoran.getImages())
                .uploadDate(dorandoran.getUploadDate())
                .build();
    }
}
