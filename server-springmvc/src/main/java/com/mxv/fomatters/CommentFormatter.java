/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mxv.fomatters;

import com.mxv.pojo.Comment;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author maixuanvinh
 */
public class CommentFormatter implements Formatter<Comment>{

    @Override
    public String print(Comment comment, Locale locale) {
        return String.valueOf(comment.getId());
    }

    @Override
    public Comment parse(String comment, Locale locale) throws ParseException {
        int id = Integer.parseInt(comment);
        return new Comment(id);
    }
    
}
