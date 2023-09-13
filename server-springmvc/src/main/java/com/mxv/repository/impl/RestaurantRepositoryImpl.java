/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mxv.repository.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.mxv.pojo.CategoryRes;
import com.mxv.pojo.City;
import com.mxv.pojo.Restaurant;
import com.mxv.pojo.User;
import com.mxv.repository.RestaurantRepository;
import com.mxv.repository.UserRepository;
import com.mxv.service.CategoryResService;
import com.mxv.service.CityService;
import com.mxv.service.UserService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author maixuanvinh
 */
@Repository
@Transactional
//@PropertySource("classpath:configs.properties")
public class RestaurantRepositoryImpl implements RestaurantRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Environment env;
    @Autowired
    private UserService userService;
    @Autowired
    private CityService cityService;
    @Autowired
    private CategoryResService cateResService;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<Restaurant> getRestaurant(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Restaurant> q = b.createQuery(Restaurant.class);
        Root root = q.from(Restaurant.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                predicates.add(b.like(root.get("name"), String.format("%%%s%%", kw)));
            }

            String cateResId = params.get("cateResId");
            if (cateResId != null && !cateResId.isEmpty()) {
                predicates.add(b.equal(root.get("categoryResId"), Integer.parseInt(cateResId)));
            }
            q.where(predicates.stream().toArray(Predicate[]::new));
        }

//        q.orderBy(b.desc(root.get("id")));
        Query query = session.createQuery(q);

        if (params != null) {
            String page = params.get("page");
            if (page != null && !page.isEmpty()) {
                int p = Integer.parseInt(page);
                int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));

                query.setMaxResults(pageSize);
                query.setFirstResult((p - 1) * pageSize);
            }
        }

        return query.getResultList();
    }

    @Override
    public Long countRestaurant() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT Count(*) FROM Restaurant");

        return Long.parseLong(q.getSingleResult().toString());
    }

    @Override
    public Restaurant getRestaurantById(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        return session.get(Restaurant.class, id);
    }

    @Override
    public boolean deleteRestaurant(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        Restaurant res = this.getRestaurantById(id);
        try {
            session.delete(res);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateRestaurant(Restaurant restaurant) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
                restaurant.setActive(true);
                s.update(restaurant);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addRestaurant(Restaurant restaurant) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.save(restaurant);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
