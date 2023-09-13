/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mxv.controllers;

import com.mxv.pojo.City;
import com.mxv.service.CityService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author maixuanvinh
 */
@RestController
@RequestMapping("/api")
public class ApiCityController {
    @Autowired
    private CityService cityService;
    
    @GetMapping("/city/")
    @CrossOrigin
    public ResponseEntity<List<City>> list() {
        return new ResponseEntity<>(this.cityService.getCity(), HttpStatus.OK);
    }
}
