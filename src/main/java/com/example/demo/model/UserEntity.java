package com.example.demo.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String first_name;

    @Column
    private String last_name;

    @Column
    private String contact;

    @Column
    private String user_role;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teamid", referencedColumnName = "id")
    private TeamEntity team_name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "positionid", referencedColumnName = "id")
    private PositionEntity position_name;


    public UserEntity() {
    }

    public UserEntity(String username, String password, String first_name, String last_name,
                      String contact, String user_role) {
        this.username = username;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.contact = contact;
        this.user_role = user_role;
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

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getUserRole() {
        return user_role;
    }

    public void setUserRole(String user_role) {
        this.user_role = user_role;
    }

    public PositionEntity getUserPositionname() {
        return position_name;
    }

    public void setPositionName(PositionEntity position_name) {
        this.position_name = position_name;
    }

    public TeamEntity getTeamname() {
        return team_name;
    }

    public void setTeamName(TeamEntity team_name) {
        this.team_name = team_name;
    }


    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", first_name='" + first_name + '\'' +
                ", lastName='" + last_name + '\'' +
                ", contact='" + contact + '\'' +
                ", userRole='" + user_role + '\'' +
                ", teamName=" + team_name +
                ", positionName=" + position_name +
                '}';
    }
}
