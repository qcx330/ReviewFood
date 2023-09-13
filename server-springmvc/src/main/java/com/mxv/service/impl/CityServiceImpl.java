/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mxv.service.impl;

import com.mxv.pojo.City;
import com.mxv.service.CityService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.mxv.repository.CityRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author maixuanvinh
 */
@Service
public class CityServiceImpl implements CityService{
    @Autowired
    private CityRepository cityRepository;
    
    @Override
    public List<City> getCity() {
        return this.cityRepository.getCity();
    }

    @Override
    public City getCityById(int id) {
        return this.cityRepository.getCityById(id);
    }
}
