package com.makive.moumi.service;

import com.makive.moumi.model.domain.Dorandoran;
import com.makive.moumi.model.dto.DorandoranDTO;
import com.makive.moumi.model.dto.request.DorandoranRequest;
import com.makive.moumi.model.dto.response.DorandoranResponse;
import com.makive.moumi.repository.DorandoranRepository;
import com.makive.moumi.repository.specification.DorandoranSpecifications;
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
public class DorandoranService {
    private final DorandoranRepository dorandoranRepository;

    public DorandoransResponse getDorandorans(DorandoranRequest dorandoranRequest, Pageable pageable) {
        List<String> category = dorandoranRequest.getCategory();
        Specification<Dorandoran> spec = DorandoranSpecifications.findAllByAllCategoryNames(category);

        Slice<Dorandoran> dorandoranSlice = dorandoranRepository.findAll(spec, pageable);
        List<DorandoranDTO> dorandoranDTOList = dorandoranSlice.getContent().stream()
                .map(DorandoranDTO::fromDorandoran)
                .collect(Collectors.toList());

        return DorandoransResponse.builder()
                .content(dorandoranDTOList)
                .first(dorandoranSlice.isFirst())
                .last(dorandoranSlice.isLast())
                .build();
    }
}