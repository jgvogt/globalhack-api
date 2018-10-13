package com.antipattrn.ambassador.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.antipattrn.ambassador.entity.Ambassador;

@Repository
public interface AmbassadorRepository extends JpaRepository<Ambassador, String> {

    @Query(value="SELECT a.* FROM ambassadors a LEFT JOIN ambassador_tags at ON a.id = at.ambassador_id LEFT JOIN tags t ON at.tag_id = t.id WHERE t.name in (:tags) GROUP BY a.id", nativeQuery = true)
    public List<Ambassador> findByTagsNameIn(@Param("tags") List<String> tags);
}
