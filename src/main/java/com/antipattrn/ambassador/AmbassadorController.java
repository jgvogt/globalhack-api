package com.antipattrn.ambassador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ambassadors")
public class AmbassadorController {

    @Autowired
    private AmbassadorRepository ambassadorRepository;

    @GetMapping
    public List<Ambassador> find() {
        return ambassadorRepository.findAll();
    }

    @PostMapping
    public Ambassador create(@RequestBody Ambassador ambassador) {
        return ambassadorRepository.save(new Ambassador(ambassador.getName(), ambassador.getDescription()));
    }

    @PutMapping("/{ambassadorId}")
    public Ambassador update(@PathVariable String ambassadorId, @RequestBody Ambassador ambassador) {
        return ambassadorRepository.save(new Ambassador(ambassadorId, ambassador.getName(), ambassador.getDescription()));
    }

    @DeleteMapping("/{ambassadorId}")
    public void delete(@PathVariable String ambassadorId) {
        ambassadorRepository.delete(ambassadorId);
    }
}
