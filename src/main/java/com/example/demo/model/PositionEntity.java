package com.example.demo.model;

import javax.persistence.*;

@Entity
public class PositionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String positionname;

    public PositionEntity() {
    }

    public PositionEntity(String positionname) {
        this.positionname = positionname;
    }

    public String getPositionname() {
        return positionname;
    }

    public void setPositionname(String positionname) {
        this.positionname = positionname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
