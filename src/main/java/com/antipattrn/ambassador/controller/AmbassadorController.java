package com.antipattrn.ambassador.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.antipattrn.ambassador.entity.Ambassador;
import com.antipattrn.ambassador.entity.Tag;
import com.antipattrn.ambassador.repository.AmbassadorRepository;
import com.antipattrn.ambassador.repository.AmbassadorSearchCriteria;

@RestController
@RequestMapping("/api/ambassadors")
public class AmbassadorController {

    @Autowired
    private AmbassadorRepository ambassadorRepository;

    @GetMapping
    public List<Ambassador> find(@RequestParam(required = false) List<String> tags,
            @RequestParam(required = false) String postalCode,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName) {
        return ambassadorRepository
                .findAll(AmbassadorSearchSpecificaton.findByCriteria(new AmbassadorSearchCriteria(tags, postalCode, firstName, lastName)));
    }

    @PostMapping
    public Ambassador create(@RequestBody Ambassador ambassador) {
        return ambassadorRepository.save(new Ambassador(ambassador.getFirstName(), ambassador.getLastName(), ambassador.getPostalCode(), ambassador.getGender(), ambassador.getStatus(), ambassador.getPhone(), ambassador.getEmail()));
    }

    @PutMapping("/{ambassadorId}")
    public Ambassador update(@PathVariable String ambassadorId, @RequestBody Ambassador ambassador) {
        return ambassadorRepository.save(new Ambassador(ambassadorId, ambassador.getFirstName(), ambassador.getLastName(), ambassador.getPostalCode(), ambassador.getGender(), ambassador.getStatus(), ambassador.getPhone(), ambassador.getEmail()));
    }

    @DeleteMapping("/{ambassadorId}")
    public void delete(@PathVariable String ambassadorId) {
        ambassadorRepository.delete(ambassadorId);
    }

    private static class AmbassadorSearchSpecificaton {
        private static Specification<Ambassador> findByCriteria(final AmbassadorSearchCriteria searchCriteria) {
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
}
