/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mxv.repository.impl;

import com.mxv.pojo.Cart;
import com.mxv.pojo.Follower;
import com.mxv.pojo.InOrder;
import com.mxv.pojo.Restaurant;
import com.mxv.pojo.User;
import com.mxv.repository.FollowerRepository;
import com.mxv.repository.RestaurantRepository;
import com.mxv.repository.UserRepository;
import com.mxv.service.FollowerService;
import com.mxv.service.UserService;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author maixuanvinh
 */
@Repository
@Transactional
public class FollowerRepositoryImpl implements FollowerRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Autowired
    private UserService userService;

    @Autowired
    private FollowerService followerService;

    @Override
    public List<Follower> getFollowerByUserId(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        javax.persistence.Query q = s.createQuery("From Follower Where userId.id=:id");
        q.setParameter("id", id);

        return q.getResultList();
    }

    @Override
    public boolean addFollow(Restaurant restaurant) {
        Session s = this.factory.getObject().getCurrentSession();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User u = this.userService.getUserByUn(authentication.getName());
        try {
            Follower follower = new Follower();
            follower.setUserId(u);
            follower.setRestaurantId(restaurant);
            s.save(follower);
            return true;
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean isFollowing(int restaurantId) {
        Session s = this.factory.getObject().getCurrentSession();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User u = this.userService.getUserByUn(authentication.getName());
        javax.persistence.Query q = s.createQuery("SELECT Count(*) From Follower Where userId.id=:userId and restaurantId.id =:restaurantId");
        q.setParameter("userId", u.getId());
        q.setParameter( "restaurantId", restaurantId);

        int count = Integer.parseInt(q.getSingleResult().toString());
        return count > 0;
    }  

    @Override
    public boolean unFollow(int resId) {
        Session session = this.factory.getObject().getCurrentSession();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User u = this.userService.getUserByUn(authentication.getName());
        javax.persistence.Query q = session.createQuery("From Follower Where userId.id=:userId and restaurantId.id =:restaurantId");
        q.setParameter("userId", u.getId());
        q.setParameter( "restaurantId", resId);
        
        Follower follower = (Follower) q.getSingleResult();
        try{
            session.delete(follower);
            return true;
        }catch(HibernateException ex){
            ex.printStackTrace();
            return false;
        }
         
    }

    @Override
    public Follower getFollowerById(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        return session.get(Follower.class, id);
    }
}
