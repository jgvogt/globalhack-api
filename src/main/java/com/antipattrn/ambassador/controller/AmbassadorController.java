package com.antipattrn.ambassador.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
import com.antipattrn.ambassador.entity.AmbassadorReview;
import com.antipattrn.ambassador.entity.Tag;
import com.antipattrn.ambassador.repository.AmbassadorRepository;
import com.antipattrn.ambassador.repository.AmbassadorSearchCriteria;
import com.antipattrn.ambassador.repository.AmbassadorSearchSpecificaton;
import com.antipattrn.ambassador.repository.TagRepository;
import com.antipattrn.ambassador.representation.Review;

@RestController
@RequestMapping("/api/ambassadors")
public class AmbassadorController {

    @Autowired
    private AmbassadorRepository ambassadorRepository;
    @Autowired
    private TagRepository tagRepository;

    @GetMapping
    public List<Ambassador> find(@RequestParam(required = false) List<String> tags,
            @RequestParam(required = false) String postalCode,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName) {
        return ambassadorRepository
                .findAll(AmbassadorSearchSpecificaton
                        .findByCriteria(new AmbassadorSearchCriteria(tags, postalCode, firstName, lastName)));
    }

    @GetMapping("/{ambassadorId}")
    public Ambassador findOne(@PathVariable String ambassadorId) {
        return ambassadorRepository.findOne(ambassadorId);
    }

    @PostMapping
    public Ambassador create(@RequestBody Ambassador newAmbassador) {
        Ambassador ambassador = new Ambassador(newAmbassador.getFirstName(), newAmbassador.getLastName(), newAmbassador.getPostalCode(),
                newAmbassador.getGender(), Ambassador.Status.ACTIVE, newAmbassador.getPhone(), newAmbassador.getEmail());

        for (Tag tag : newAmbassador.getTags()) {
            ambassador.getTags().add(tagRepository.findOne(tag.getId()));
        }

        return ambassadorRepository.save(ambassador);
    }

    @PutMapping("/{ambassadorId}")
    public Ambassador update(@PathVariable String ambassadorId, @RequestBody Ambassador ambassador) {
        return ambassadorRepository.save(new Ambassador(ambassadorId, ambassador.getFirstName(), ambassador.getLastName(), ambassador.getPostalCode(), ambassador.getGender(), ambassador.getStatus(), ambassador.getPhone(), ambassador.getEmail()));
    }

    @DeleteMapping("/{ambassadorId}")
    public void delete(@PathVariable String ambassadorId) {
        ambassadorRepository.delete(ambassadorId);
    }

    @PostMapping("/{ambassadorId}/reviews")
    @Transactional
    public Ambassador createReview(@PathVariable String ambassadorId, @RequestBody Review review) {
        Ambassador ambassador = ambassadorRepository.findOne(ambassadorId);
        ambassador.getReviews().add(new AmbassadorReview(ambassador, review.getRating(), review.getReview()));

        return ambassador;
    }

}
