package com.antipattrn.ambassador.controller;

import com.antipattrn.ambassador.entity.Tag;
import com.antipattrn.ambassador.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    @Autowired
    private TagRepository tagRepository;

    @GetMapping
    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    @GetMapping("/name")
    public List<Tag> findByName(@RequestParam String name) {
        return tagRepository.findByNameIgnoreCaseContaining(name);
    }

    @GetMapping("/type")
    public List<Tag> findByType(@RequestParam String type) {
        return tagRepository.findByTypeIgnoreCaseContaining(type);
    }
}
