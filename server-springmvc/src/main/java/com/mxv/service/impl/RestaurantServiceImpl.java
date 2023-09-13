/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mxv.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.mxv.pojo.CategoryRes;
import com.mxv.pojo.City;
import com.mxv.pojo.Restaurant;
import com.mxv.pojo.User;
import com.mxv.repository.RestaurantRepository;
import com.mxv.repository.impl.RestaurantRepositoryImpl;
import com.mxv.service.CategoryResService;
import com.mxv.service.CityService;
import com.mxv.service.RestaurantService;
import com.mxv.service.UserService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author maixuanvinh
 */
@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepo;
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private UserService userService;
    @Autowired
    private CityService cityService;
    @Autowired
    private CategoryResService categoryResService;

    @Override
    public List<Restaurant> getRestaurant(Map<String, String> params) {
        return this.restaurantRepo.getRestaurant(params);
    }

    @Override
    public Long countRestaurant() {
        return this.restaurantRepo.countRestaurant();
    }

    @Override
    public boolean updateRestaurant(Restaurant res) {
        return this.restaurantRepo.updateRestaurant(res);
    }

    @Override
    public Restaurant getRestaurantById(int id) {
        return this.restaurantRepo.getRestaurantById(id);
    }

    @Override
    public boolean deleteRestaurant(int id) {
        return this.restaurantRepo.deleteRestaurant(id);
    }

    @Override
    public Restaurant addRestaurant(Map<String, String> params, MultipartFile image) {
        Restaurant restaurant = new Restaurant();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User u = this.userService.getUserByUn(authentication.getName());
        restaurant.setUserId(u);
        City city = this.cityService.getCityById(Integer.parseInt(params.get("cityId")));
        CategoryRes cate = this.categoryResService.getCateResById(Integer.parseInt(params.get("categoryResId")));
        restaurant.setCityId(city);
        restaurant.setCategoryResId(cate);
        restaurant.setName(params.get("name"));
        restaurant.setAddress(params.get("address"));
        restaurant.setActive(false);
        if (!image.isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(image.getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                restaurant.setImage(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(RestaurantServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.restaurantRepo.addRestaurant(restaurant);
        return restaurant;
    }
}
