package com.example.demo.model;


import javax.persistence.*;

@Entity
public class TeamEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String teamname;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "projectid", referencedColumnName = "id")
    private PositionEntity projectname;


    public TeamEntity() {
    }

    public TeamEntity(String teamname) {
        this.teamname = teamname;
    }

    public String getTeam() {
        return teamname;
    }

    public void setTeam(String teamname) {
        this.teamname = teamname;
    }

    public PositionEntity getProject() {
        return projectname;
    }

    public void setProject(PositionEntity projectname) {
        this.projectname = projectname;
    }
}
