package com.makive.moumi.repository.specification;

import com.makive.moumi.model.domain.Translation;
import com.makive.moumi.model.domain.TranslationCategory;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class TranslationSpecifications {
    public static Specification<Translation> findAllByAllCategoryNamesAndPriceRange(List<String> category, Integer minPrice, Integer maxPrice) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (!category.isEmpty()) {
                Subquery<Long> subquery = query.subquery(Long.class);
                Root<TranslationCategory> subqueryTranslationCategory = subquery.from(TranslationCategory.class);
                subquery.select(subqueryTranslationCategory.get("translation").get("id"));
                subquery.groupBy(subqueryTranslationCategory.get("translation").get("id"));
                subquery.having(criteriaBuilder.equal(criteriaBuilder.count(subqueryTranslationCategory.get("category").get("name")), category.size()));
                subquery.where(subqueryTranslationCategory.get("category").get("name").in(category));

                predicates.add(root.get("id").in(subquery));
            }

            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice));
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
