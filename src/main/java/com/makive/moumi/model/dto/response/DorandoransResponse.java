package com.makive.moumi.model.dto.response;

import com.makive.moumi.model.dto.DorandoranDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DorandoransResponse {
    private List<DorandoranDTO> content;
    private boolean first;
    private boolean last;
}
