package com.justdoit.POJOs;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="house")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int houseId;
    @NotNull
    private String houseName;

    public House() {
    }

    public int getHouseId() {
        return houseId;
    }

    public void setHouseId(int houseId) {
        this.houseId = houseId;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

}
