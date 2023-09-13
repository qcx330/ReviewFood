/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mxv.controllers;

import com.mxv.pojo.Comment;
import com.mxv.pojo.Menu;
import com.mxv.pojo.Restaurant;
import com.mxv.service.CategoryResService;
import com.mxv.service.CityService;
import com.mxv.service.CommentService;
import com.mxv.service.MenuService;
import com.mxv.service.RestaurantService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author maixuanvinh
 */
@RestController
@RequestMapping("/api")
public class ApiRestaurantController {
    @Autowired
    private MenuService menuService;
    
    @Autowired
    private RestaurantService restaurantService;
    
    @Autowired
    private CommentService commentService;
    
    @RequestMapping("/restaurant/")
    @CrossOrigin
    public ResponseEntity<List<Restaurant>> list(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.restaurantService.getRestaurant(params), HttpStatus.OK);
    }
    
    @DeleteMapping("/restaurant/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") int id){
        this.restaurantService.deleteRestaurant(id);
    }
    
    @RequestMapping(path = "/restaurant/{resId}/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Restaurant> details(@PathVariable(value = "resId") int id) {
        return new ResponseEntity<>(this.restaurantService.getRestaurantById(id), HttpStatus.OK);
    }
    
    @GetMapping("/restaurant/{resId}/menu/")
    @CrossOrigin
    public ResponseEntity<List<Menu>> ListMenus(@PathVariable(value = "resId") int id) {
        return new ResponseEntity<>(this.menuService.getMenuByRestaurantId(id), HttpStatus.OK);
    }
    
    @GetMapping("/restaurant/{restaurantId}/comments/")
    @CrossOrigin
    public ResponseEntity<List<Comment>> listComments(@PathVariable(value = "restaurantId") int id) {
        return new ResponseEntity<>(this.commentService.getCommentByRestaurantId(id), HttpStatus.OK);
    }

    //        MediaType.MULTIPART_FORM_DATA_VALUE,    
//    , @RequestPart MultipartFile[] file
    @PostMapping(path = "/restaurant/add", 
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, 
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @CrossOrigin
    public ResponseEntity<Restaurant> add(@RequestParam Map<String, String> restaurant, @RequestPart(value = "image", required = false ) MultipartFile image) {
      Restaurant res = this.restaurantService.addRestaurant(restaurant, image);
      return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
}
