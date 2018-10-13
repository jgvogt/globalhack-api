package com.antipattrn.ambassador.repository;

import com.antipattrn.ambassador.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, String> {
    List<Tag> findByNameIgnoreCaseContaining(String name);
}
