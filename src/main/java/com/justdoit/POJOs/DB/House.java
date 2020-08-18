package com.justdoit.POJOs.DB;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Table(name="house")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int houseId;
    @NotEmpty(message = "Household name may not be empty")
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
