package com.antipattrn.ambassador.controller;

import com.antipattrn.ambassador.entity.Tag;
import com.antipattrn.ambassador.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class TagController {
    @Autowired
    private TagRepository tagRepository;

    @GetMapping
    public List<Tag> find(@RequestParam String name) {
        return tagRepository.findByNameContaining(name);
    }
}
