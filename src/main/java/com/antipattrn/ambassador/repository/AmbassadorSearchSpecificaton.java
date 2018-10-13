package com.antipattrn.ambassador.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.antipattrn.ambassador.entity.Ambassador;
import com.antipattrn.ambassador.entity.Tag;

public class AmbassadorSearchSpecificaton {
    public static Specification<Ambassador> findByCriteria(final AmbassadorSearchCriteria searchCriteria) {
        return new Specification<Ambassador>() {
            @Override
            public Predicate toPredicate(Root<Ambassador> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();

                if (searchCriteria.getTags() != null && !searchCriteria.getTags().isEmpty()) {
                    Join<Ambassador, Tag> tags = root.join("tags");
                    predicates.add(tags.get("name").in(searchCriteria.getTags()));
                }

                if (searchCriteria.getPostalCode() != null) {
                    predicates.add(cb.equal(root.get("postalCode"), searchCriteria.getPostalCode()));
                }

                if (searchCriteria.getFirstName() != null) {
                    predicates.add(cb.equal(root.get("firstName"), searchCriteria.getFirstName()));
                }

                if (searchCriteria.getLastName() != null) {
                    predicates.add(cb.equal(root.get("lastName"), searchCriteria.getLastName()));
                }

                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
