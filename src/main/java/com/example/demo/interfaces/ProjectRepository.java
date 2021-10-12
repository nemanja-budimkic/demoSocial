package com.example.demo.interfaces;

import com.example.demo.model.ProjectEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;

public interface ProjectRepository extends CrudRepository<ProjectEntity, Long> {


    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = " UPDATE project_entity SET clientid = :clientid WHERE  projectname= :projectname", nativeQuery = true)
    void updateProjectId(int clientid, String projectname);

    @Query(value = " SELECT * FROM project_entity", nativeQuery = true)
    ArrayList<ProjectEntity> getAllProjects();
}
