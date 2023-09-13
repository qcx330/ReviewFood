/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mxv.controllers;

import com.mxv.service.CategoryResService;
import com.mxv.service.CityService;
import com.mxv.service.RestaurantService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author maixuanvinh
 */
@Controller
@ControllerAdvice
@PropertySource("classpath:configs.properties")
public class IndexController {
    @Autowired
    private RestaurantService restaurantService;
    
    @Autowired
    private CityService cityService;
    
    @Autowired
    private CategoryResService categoryService;
   
    
    @Autowired
    private Environment env;
    
    @ModelAttribute
    public void commonAttr(Model model){
        model.addAttribute("categoryRes", this.categoryService.getCategoryRes());
        model.addAttribute("city", this.cityService.getCity());
    }
    
    @RequestMapping("/")
    public String index(Model model, @RequestParam Map<String, String> params){
        model.addAttribute("restaurant", this.restaurantService.getRestaurant(params));
        
        int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
        long count = this.restaurantService.countRestaurant();
        model.addAttribute("countRestaurant", Math.ceil((count * 1.0) / pageSize));
        return "index";
    }
}
