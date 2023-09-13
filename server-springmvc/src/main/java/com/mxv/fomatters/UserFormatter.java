/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mxv.fomatters;

import com.mxv.pojo.User;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author maixuanvinh
 */
public class UserFormatter implements Formatter<User>{

    @Override
    public String print(User user, Locale locale) {
        return String.valueOf(user.getId());
    }

    @Override
    public User parse(String user, Locale locale) throws ParseException {
        int id = Integer.parseInt(user);
        return new User(id);
    }
    
}
