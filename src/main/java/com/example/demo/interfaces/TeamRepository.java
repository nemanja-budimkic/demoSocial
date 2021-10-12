package com.example.demo.interfaces;


import com.example.demo.model.TeamEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

public interface TeamRepository extends CrudRepository<TeamEntity, Long> {

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = " UPDATE team_entity SET projectid = :projectid WHERE teamname = :teamname", nativeQuery = true)
    void updatePosId(int projectid, String teamname);

    @Query(value = " SELECT * FROM team_entity", nativeQuery = true)
    ArrayList<TeamEntity> getAllTeams();

}
