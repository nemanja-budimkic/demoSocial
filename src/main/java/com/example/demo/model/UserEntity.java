package com.example.demo.model;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.*;


@Entity
public class UserEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String firstname;

    @Column
    private String lastname;

    @Column
    private String contact;

    @Column
    private String userrole;





   /* @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teamid", referencedColumnName = "id")
    private TeamEntity team;*/

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "positionid", referencedColumnName = "id")
    private PositionEntity positionname;


    public UserEntity() {
    }

    public UserEntity(String username, String password, String firstname, String lastname,
                      String contact, String userrole) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.contact = contact;
        this.userrole = userrole;
    }

    public int getid() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getfirstname() {
        return firstname;
    }

    public void setfirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getlastname() {
        return lastname;
    }

    public void setlastname(String lastname) {
        this.lastname = lastname;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getUserRole() {
        return userrole;
    }

    public void setUserRole(String userrole) {
        this.userrole = userrole;
    }


    public PositionEntity getUserPositionname() {
        return positionname;
    }

    public void setPositionname(PositionEntity positionname) {
        this.positionname = positionname;
    }
}
