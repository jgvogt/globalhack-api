package com.antipattrn.ambassador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.antipattrn.ambassador.entity.Ambassador;

@Repository
public interface AmbassadorRepository extends JpaRepository<Ambassador, String>, JpaSpecificationExecutor<Ambassador> {

}
