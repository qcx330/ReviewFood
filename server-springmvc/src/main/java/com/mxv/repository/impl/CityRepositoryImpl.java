/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mxv.repository.impl;

import com.mxv.pojo.City;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import com.mxv.repository.CityRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author maixuanvinh
 */
@Repository
@Transactional
public class CityRepositoryImpl implements CityRepository{
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<City> getCity() {
        Session session = factory.getObject().getCurrentSession();
        Query query = session.createQuery("FROM City");
        return query.getResultList();
    }

    @Override
    public City getCityById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(City.class, id);
    }
    
}
