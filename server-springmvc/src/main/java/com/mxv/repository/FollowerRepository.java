/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mxv.repository;

import com.mxv.pojo.Follower;
import com.mxv.pojo.Restaurant;
import java.util.List;

/**
 *
 * @author maixuanvinh
 */
public interface FollowerRepository {
    List<Follower> getFollowerByUserId(int id);
    Follower getFollowerById(int id);
    boolean addFollow(Restaurant restaurant);
    boolean isFollowing(int restaurantId);
    boolean unFollow(int id);
}
