package com.example.demo.model;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import java.util.ArrayList;


@Repository
public interface UsersRepository extends CrudRepository<UserEntity, Long> {

    @Query(value = " SELECT username FROM user_entity WHERE username = :username ", nativeQuery = true)
    String getUsernameRepo(String username);

    @Query(value = " SELECT username FROM user_entity WHERE username = :username and password = :password ", nativeQuery = true)
    String getUserPassMatch(String username, String password);

    @Query(value = " SELECT firstname FROM user_entity WHERE firstname = :firstname ", nativeQuery = true)
    String getfirstnameRepo(String firstname);

    @Query(value = " SELECT lastname FROM user_entity WHERE firstname = :firstname ", nativeQuery = true)
    String getlastnameCookie(String firstname);

    @Query(value = " SELECT username FROM user_entity WHERE firstname = :firstname ", nativeQuery = true)
    String getUsernameCookie(String firstname);

    @Query(value = " SELECT contact FROM user_entity WHERE firstname = :firstname ", nativeQuery = true)
    String getContactCookie(String firstname);

    @Query(value = " SELECT firstname FROM user_entity WHERE username = :username ", nativeQuery = true)
    String getNameForCookie(String username);

    @Query(value = " SELECT lastname FROM user_entity WHERE username = :username ", nativeQuery = true)
    String getLastNfromUsern(String username);

    @Query(value = " SELECT contact FROM user_entity WHERE username = :username ", nativeQuery = true)
    String getContactfromUsern(String username);


    @Query(value = " SELECT firstname FROM user_entity WHERE id = :id", nativeQuery = true)
    String getFirstTable(int id);

    @Query(value = " SELECT lastname FROM user_entity WHERE id = :id", nativeQuery = true)
    String getLastTable(int id);

    @Query(value = " SELECT contact FROM user_entity WHERE id = :id", nativeQuery = true)
    String getContactTable(int id);

    @Query(value = " SELECT userrole FROM user_entity WHERE username = :username", nativeQuery = true)
    String getUserRole(String username);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = " UPDATE user_entity SET userrole = :newrole WHERE username = :username", nativeQuery = true)
    void setUserRole(String username, String newrole);

    @Query(value = " SELECT userrole FROM user_entity WHERE id = :id", nativeQuery = true)
    String getUserRoleTable(int id);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = " DELETE FROM user_entity WHERE username = :username", nativeQuery = true)
    void deleteUser(String username);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = " UPDATE user_entity SET id = :id WHERE username = :username", nativeQuery = true)
    void resetId(int id, String username);

    @Query(value = " SELECT id FROM user_entity WHERE username = :username", nativeQuery = true)
    int getId(String username);

    @Query(value = " SELECT username FROM user_entity WHERE id = :id", nativeQuery = true)
    String getUserWithId(int id);

    @Query(value = " SELECT * FROM user_entity", nativeQuery = true)
    ArrayList<UserEntity> getAllUsers();

    @Query(value = " SELECT username, firstname, lastname, contact, userrole FROM user_entity WHERE username=  :username", nativeQuery = true)
    String getUserInfo(String username);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = " UPDATE user_entity SET positionid = :positionid WHERE username = :username", nativeQuery = true)
    void updatePosId(int positionid, String username);



}



