/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mxv.repository.impl;

import com.mxv.pojo.Menu;
import com.mxv.repository.MenuRepository;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author maixuanvinh
 */
@Repository
@Transactional
public class MenuRepositoryImpl implements MenuRepository{
    
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Menu> getMenuByRestaurantId(int resId) {
        Session s = this.factory.getObject().getCurrentSession();
        javax.persistence.Query q = s.createQuery("From Menu Where restaurantId.id=:id");
        q.setParameter("id", resId);
        
        return q.getResultList();
    }
}
