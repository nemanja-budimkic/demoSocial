package com.example.demo.spring;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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



}



