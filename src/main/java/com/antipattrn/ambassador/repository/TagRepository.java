package com.antipattrn.ambassador.repository;

import com.antipattrn.ambassador.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, String> {
    List<Tag> findByNameIgnoreCaseContaining(String name);
    List<Tag> findByTypeIgnoreCaseContaining(String type);
}
