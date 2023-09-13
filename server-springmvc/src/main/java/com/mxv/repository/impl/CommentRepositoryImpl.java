/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mxv.repository.impl;

import com.mxv.pojo.Comment;
import com.mxv.repository.CommentRepository;
import java.util.List;
import org.hibernate.HibernateException;
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
public class CommentRepositoryImpl implements CommentRepository{
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Comment> getCommentByRestaurantId(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        javax.persistence.Query q = s.createQuery("From Comment Where restaurantId.id=:id");
        q.setParameter("id", id);
        
        return q.getResultList();
    }

    @Override
    public Comment addComment(Comment c) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.save(c);
            return c;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    
}
