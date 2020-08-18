package com.justdoit.service;

import com.justdoit.POJOs.DB.House;
import com.justdoit.repositories.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


//the service uses methods from the Repo interface
@Service
public class HouseService {

    @Autowired
    private HouseRepository houseRepo;


    public List<House> listAllHouses() {
        return houseRepo.findAll();
    }

    public House listByHouseId(int houseId) {
        return houseRepo.findOne(houseId);
    }

    public House listByHouseName(String houseName) {
        return houseRepo.findByHouseName(houseName);
    }

    public House saveHouse(House house) {
        return houseRepo.save(house);
    }

    public void deleteHouse(int houseId) {
        houseRepo.deleteByHouseId(houseId);
    }


}
