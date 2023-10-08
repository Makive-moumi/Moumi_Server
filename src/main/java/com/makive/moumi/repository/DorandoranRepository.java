package com.makive.moumi.repository;

import com.makive.moumi.model.domain.Dorandoran;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DorandoranRepository extends JpaRepository<Dorandoran, Long> {
    Slice<Dorandoran> findAll(Specification<Dorandoran> spec, Pageable pageable);
}
