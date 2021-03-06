package com.example.demo.interfaces;

import com.example.demo.model.PositionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface PositionRepository extends CrudRepository<PositionEntity, Long> {

    @Query(value = " SELECT * FROM position_entity", nativeQuery = true)
    ArrayList<PositionEntity> getAllPositions();


}
