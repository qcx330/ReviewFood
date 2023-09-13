/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mxv.repository;

import com.mxv.pojo.City;
import java.util.List;

/**
 *
 * @author maixuanvinh
 */
public interface CityRepository {
    List<City> getCity();
    City getCityById(int id);
}
