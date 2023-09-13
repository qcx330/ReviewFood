/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mxv.repository.impl;

import com.mxv.pojo.CategoryRes;
import com.mxv.repository.CategoryResRepository;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
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
public class CategoryResRepositoryImpl implements CategoryResRepository{
    @Autowired
    private LocalSessionFactoryBean factory;


   @Override
    public List<CategoryRes> getCategoryRes() {
       Session session = this.factory.getObject().getCurrentSession();
       Query q = session.createQuery("FROM CategoryRes");
       return q.getResultList();
    }

    @Override
    public CategoryRes getCateResById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(CategoryRes.class, id);
    }
}
