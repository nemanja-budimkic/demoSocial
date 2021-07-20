package com.example.demo.model;

import javax.persistence.*;

@Entity
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String projectname;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "clientid", referencedColumnName = "id")
    private PositionEntity client;

    public ProjectEntity() {
    }

    public ProjectEntity(String projectname) {
        this.projectname = projectname;
    }


    public String getProject() {
        return projectname;
    }

    public void setProject(String projectname) {
        this.projectname = projectname;
    }

    public PositionEntity getClient() {
        return client;
    }

    public void setClient(PositionEntity client) {
        this.client = client;
    }
}
