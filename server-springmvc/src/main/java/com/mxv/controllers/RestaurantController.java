/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mxv.controllers;

import com.mxv.pojo.Restaurant;
import com.mxv.service.RestaurantService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author maixuanvinh
 */
@Controller
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;
    
    
    @GetMapping("/restaurant")
    public String list(Model model){
        model.addAttribute("restaurant", new Restaurant());
        return "restaurant";
    }
    
    @GetMapping("/restaurant/{id}")
    public String update(Model model, @PathVariable(value = "id") int id)  {
        model.addAttribute("restaurant", this.restaurantService.getRestaurantById(id));
        return "restaurant";
    }
    
    @PostMapping("/restaurant")
    public String add(@ModelAttribute(value = "restaurant") @Valid Restaurant res, 
            BindingResult rs) {
        if (!rs.hasErrors())
            if (restaurantService.updateRestaurant(res) == true)
                return "redirect:/";
        
        return "restaurant";
    }
}
