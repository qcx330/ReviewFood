/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mxv.service.impl;

import com.mxv.pojo.Follower;
import com.mxv.pojo.Restaurant;
import com.mxv.pojo.User;
import com.mxv.repository.FollowerRepository;
import com.mxv.service.FollowerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author maixuanvinh
 */
@Service
public class FollowerServiceImpl implements FollowerService{
    @Autowired
    private FollowerRepository followerRepo;

    @Override
    public List<Follower> getFollowerByUserId(int id) {
        return this.followerRepo.getFollowerByUserId(id);
    }
    
    @Override
    public boolean addFollow(Restaurant restaurant) {
        return this.followerRepo.addFollow(restaurant);
    }

    @Override
    public boolean isFollowing(int restaurantId) {
        return this.followerRepo.isFollowing(restaurantId);
    }

    @Override
    public Follower getFollowerById(int id) {
        return this.followerRepo.getFollowerById(id);
    }

    @Override
    public boolean unFollow(int id) {
        return this.followerRepo.unFollow(id);
    }

}
