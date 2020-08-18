package com.justdoit.repositories;

import com.justdoit.POJOs.DB.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseRepository extends JpaRepository<House, Integer> {

    House findByHouseName(String houseName);

//    @Modifying
//    @Query(value = "INSERT INTO house (houseName) VALUES (:houseName)", nativeQuery = true)
//    void saveHouse(@Param("houseName") String housename);

    void deleteByHouseId(int houseId);

}
