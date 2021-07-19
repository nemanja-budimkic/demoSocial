package com.example.demo.model;


import javax.persistence.*;

@Entity
public class AdminEntity {


    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String adminkey;

    public AdminEntity() {
    }

    public AdminEntity(String adminkey) {
        this.adminkey = adminkey;
    }

    public String getAdminkey() {
        return adminkey;
    }

    public void setAdminkey(String adminkey) {
        this.adminkey = adminkey;
    }
}
