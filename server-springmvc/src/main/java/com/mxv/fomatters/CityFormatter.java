/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mxv.fomatters;

import com.mxv.pojo.City;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author maixuanvinh
 */
public class CityFormatter implements Formatter<City> {

    @Override
    public String print(City city, Locale locale) {
        return String.valueOf(city.getId());
    }

    @Override
    public City parse(String city, Locale locale) throws ParseException {
        int id = Integer.parseInt(city);
        return new City(id);
    }
    
}
