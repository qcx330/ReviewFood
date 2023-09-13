/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mxv.controllers;

import com.mxv.pojo.Follower;
import com.mxv.pojo.Restaurant;
import com.mxv.pojo.User;
import com.mxv.service.FollowerService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author maixuanvinh
 */
@RestController
@RequestMapping("/api")
public class ApiFollowerController {
    @Autowired
    private FollowerService followerService;
    
    @GetMapping("/follower/{userId}/user/")
    @CrossOrigin
    public ResponseEntity<List<Follower>> listComments(@PathVariable(value = "userId") int id) {
        return new ResponseEntity<>(this.followerService.getFollowerByUserId(id), HttpStatus.OK);
    }
    
    @PostMapping("/follow/")
    @CrossOrigin
    public ResponseEntity<String> follow(@RequestBody Restaurant restaurantId) {
        if(this.followerService.isFollowing(restaurantId.getId()))
            return new ResponseEntity<>("Following", HttpStatus.OK);
        else{
            if(this.followerService.addFollow(restaurantId))
                return new ResponseEntity<>("Follow", HttpStatus.CREATED);
            return new ResponseEntity<>("Follow", HttpStatus.BAD_REQUEST);
        }
    }
    
//    @GetMapping("/check-follow")
//    @CrossOrigin
//    public ResponseEntity<String> check(@RequestParam int restaurantId ) {
//        if (this.followerService.isFollowing(restaurantId))
//            return ResponseEntity.ok("Following");
//        return ResponseEntity.ok("Follow");
//    }
    
    @DeleteMapping("/unfollow/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CrossOrigin
    public void unfollow(@RequestBody Restaurant restaurant) {
        if(this.followerService.isFollowing(restaurant.getId()))
            this.followerService.unFollow(restaurant.getId());
    }
}
