/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mxv.service;

import com.mxv.pojo.Comment;
import java.util.List;

/**
 *
 * @author maixuanvinh
 */
public interface CommentService {
    List<Comment> getCommentByRestaurantId(int id);
    Comment addComment(Comment c);
}
