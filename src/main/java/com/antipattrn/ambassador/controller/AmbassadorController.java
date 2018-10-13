package com.antipattrn.ambassador.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.antipattrn.ambassador.repository.AmbassadorRepository;
import com.antipattrn.ambassador.repository.AmbassadorSearchCriteria;
import com.antipattrn.ambassador.repository.AmbassadorSearchSpecificaton;

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
                .findAll(AmbassadorSearchSpecificaton
                        .findByCriteria(new AmbassadorSearchCriteria(tags, postalCode, firstName, lastName)));
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

}
