package com.example.demo.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface PositionRepository extends CrudRepository<PositionEntity, Long> {

    @Query(value = " SELECT * FROM position_entity", nativeQuery = true)
    ArrayList<PositionEntity> getAllPositions();


}
