/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mxv.service;

import com.mxv.pojo.Restaurant;
import java.util.List;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author maixuanvinh
 */
public interface RestaurantService {
    List<Restaurant> getRestaurant(Map<String, String> params);
    Long countRestaurant();
    Restaurant getRestaurantById(int id);
    boolean deleteRestaurant(int id);
    boolean updateRestaurant(Restaurant res);
    Restaurant addRestaurant(Map<String, String> params, MultipartFile image);
}
