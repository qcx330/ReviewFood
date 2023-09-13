/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mxv.repository.impl;

import com.mxv.pojo.MenuItem;
import com.mxv.repository.MenuItemRepository;
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
public class MenuItemRepositoryImpl implements MenuItemRepository{
    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Override
    public List<MenuItem> getMenuItemByMenuId(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        javax.persistence.Query q = s.createQuery("From MenuItem Where menuId.id=:id");
        q.setParameter("id", id);
        
        return q.getResultList();
    }

    @Override
    public MenuItem getMenuItemById(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        return session.get(MenuItem.class, id);
    }
    
}
