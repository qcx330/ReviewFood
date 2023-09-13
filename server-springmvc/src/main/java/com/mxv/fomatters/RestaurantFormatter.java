/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mxv.fomatters;

import com.mxv.pojo.Restaurant;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author maixuanvinh
 */
public class RestaurantFormatter implements Formatter<Restaurant> {

    @Override
    public String print(Restaurant restaurant, Locale locale) {
        return String.valueOf(restaurant.getId());
    }

    @Override
    public Restaurant parse(String restaurant, Locale locale) throws ParseException {
        int id = Integer.parseInt(restaurant);
        return new Restaurant(id);
    }
    
}
