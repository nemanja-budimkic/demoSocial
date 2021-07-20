package com.example.demo.model;


import javax.persistence.*;

@Entity
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String clientname;

    @Column
    private String clientcontact;


    public ClientEntity() {
    }

    public ClientEntity(String clientname, String clientcontact) {
        this.clientname = clientname;
        this.clientcontact = clientcontact;
    }

    public String getClientName() {
        return clientname;
    }

    public void setClientName(String clientname) {
        this.clientname = clientname;
    }

    public String getContact() {
        return clientcontact;
    }

    public void setContact(String clientcontact) {
        this.clientcontact = clientcontact;
    }
}
