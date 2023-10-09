package com.makive.moumi.repository.specification;

import com.makive.moumi.model.domain.Dorandoran;
import com.makive.moumi.model.domain.DorandoranCategory;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class DorandoranSpecifications {
    public static Specification<Dorandoran> findAllByCategory(List<String> category) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (!category.isEmpty()) {
                Subquery<Long> subquery = query.subquery(Long.class);
                Root<DorandoranCategory> subqueryDorandoranCategory = subquery.from(DorandoranCategory.class);
                subquery.select(subqueryDorandoranCategory.get("translation").get("id"));
                subquery.groupBy(subqueryDorandoranCategory.get("translation").get("id"));
                subquery.having(criteriaBuilder.equal(criteriaBuilder.count(subqueryDorandoranCategory.get("category").get("name")), category.size()));
                subquery.where(subqueryDorandoranCategory.get("category").get("name").in(category));

                predicates.add(root.get("id").in(subquery));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
