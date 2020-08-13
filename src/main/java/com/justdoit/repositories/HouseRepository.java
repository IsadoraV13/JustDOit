package com.justdoit.repositories;

import com.justdoit.POJOs.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseRepository extends JpaRepository<House, Integer> {

    House findByHouseName(String houseName);

    void deleteByHouseId(int houseId);

}
