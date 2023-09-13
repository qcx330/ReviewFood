/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mxv.repository.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.mxv.pojo.City;
import com.mxv.pojo.User;
import com.mxv.repository.UserRepository;
import com.mxv.service.CityService;
import com.mxv.service.impl.UserServiceImpl;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author quynh
 */
@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private BCryptPasswordEncoder passEncoder;
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private CityService cityService;

    @Override
    public User getUserByUserName(String userName) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM User WHERE userName=:un");
        q.setParameter("un", userName);

        return (User) q.getSingleResult();
    }

    @Override
    public boolean authUser(String userName, String password) {
        User u = this.getUserByUserName(userName);
        return this.passEncoder.matches(password, u.getPassword());
    }

    @Override
    public boolean addUser(User u) {
        Session session = this.factory.getObject().getCurrentSession();
//        u.setUserRole("ROLE_USER");
//        u.setTimeJoined(new Date());        
//        if (!u.getFile().isEmpty()) {
//            try {
//                Map res = this.cloudinary.uploader().upload(u.getFile().getBytes(),
//                        ObjectUtils.asMap("resource_type", "auto"));
//                u.setAvatar(res.get("secure_url").toString());
//            } catch (IOException ex) {
//                Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
//                return false;
//            } catch (HibernateException ex) {
//                System.err.println(ex.getMessage());
//                return false;
//            }
//        }
        try {
            session.save(u);
            return true;
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateUser(User user) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            session.update(user);
            return true;
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public List<User> getUserRestaurant() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM User WHERE userRole=:un");
        q.setParameter("un", "ROLE_RESTAURANT");
        return q.getResultList();
    }

    @Override
    public User getUserById(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        return session.get(User.class, id);
    }

    @Override
    public boolean checkUser(String userName) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM User WHERE userName=:un");
        q.setParameter("un", userName);
        if (q.getSingleResult() == null) {
            return false;
        }
        return true;
    }
}
