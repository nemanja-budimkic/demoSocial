package com.example.demo.model;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

public interface AdminRepository extends CrudRepository<AdminEntity, Long> {

    @Query(value = " SELECT adminkey FROM admin_entity", nativeQuery = true)
    String getAdminKey();

    @Query(value = " SELECT adminkey FROM admin_entity where id = :id", nativeQuery = true)
    String getAdminKeyById(int id);

    @Query(value = " SELECT * FROM admin_entity", nativeQuery = true)
    ArrayList<AdminEntity> getAllKeys();

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = " UPDATE admin_entity SET adminkey = :adminkey WHERE id= :id", nativeQuery = true)
    void updateKey(String adminkey, int id);



}
