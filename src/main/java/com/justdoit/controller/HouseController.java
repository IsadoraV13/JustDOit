package com.justdoit.controller;

import com.justdoit.POJOs.DB.House;
import com.justdoit.POJOs.ResponseObject;
import com.justdoit.service.HouseService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/houses")
public class HouseController {

    @Autowired
    private HouseService houseService;

    @GetMapping
    // This should be an admin function
    public ResponseObject<List<House>> viewAllHouses() {
        ResponseObject<List<House>> res = new ResponseObject();
        res.setData(houseService.listAllHouses());
        return res;
    }

    @GetMapping("/{houseId}")
    // return house info, e.g. name, deadline, etc
    public ResponseObject<House> viewHouse(@PathVariable(value="houseId")int houseId) {
        ResponseObject<House> res = new ResponseObject();
        res.setData(houseService.listByHouseId(houseId));
        return res;
    }


//    @GetMapping("/{houseId}/members")
//    // return house info, e.g. name, deadline, etc
//    public ResponseObject<House> viewHousehold(@RequestBody House newHouse) {
//        ResponseObject<House> res = new ResponseObject();
//        res.setData(houseService.saveHouse(newHouse));
//        return res;
//    }

    @DeleteMapping("/{houseId}")
    public void deleteHouse(@PathVariable(value="houseId")int houseId) throws ObjectNotFoundException {
        houseService.deleteHouse(houseId);
    }

}
