/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mxv.service.impl;

import com.mxv.pojo.Comment;
import com.mxv.pojo.User;
import com.mxv.repository.CommentRepository;
import com.mxv.repository.UserRepository;
import com.mxv.service.CommentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 *
 * @author maixuanvinh
 */
@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentRepository commentRepo;
    
    @Autowired
    private UserRepository userRepo;

    @Override
    public List<Comment> getCommentByRestaurantId(int id) {
        return this.commentRepo.getCommentByRestaurantId(id);
    }

    @Override
    public Comment addComment(Comment c) {
//        c.setCreatedDate(new Date());
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User u = this.userRepo.getUserByUserName(authentication.getName());
        c.setUserId(u);
        
        return this.commentRepo.addComment(c);
    }
    
    
}
