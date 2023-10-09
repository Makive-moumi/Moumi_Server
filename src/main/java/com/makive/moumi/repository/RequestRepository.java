package com.makive.moumi.repository;

import com.makive.moumi.model.domain.Request;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    Slice<Request> findAll(Specification<Request> spec, Pageable pageable);
}
