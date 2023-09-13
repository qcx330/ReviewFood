/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mxv.fomatters;

import com.mxv.pojo.Follower;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author maixuanvinh
 */
public class FollowerFormatter implements Formatter<Follower>{

    @Override
    public String print(Follower follower, Locale locale) {
        return String.valueOf(follower.getId());
    }

    @Override
    public Follower parse(String follower, Locale locale) throws ParseException {
        int id = Integer.parseInt(follower);
        return new Follower(id);
    }
    
}
