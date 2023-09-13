/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mxv.fomatters;

import com.mxv.pojo.MenuItem;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author maixuanvinh
 */
public class MenuItemFormatter implements Formatter<MenuItem>{

    @Override
    public String print(MenuItem menuItem, Locale locale) {
        return String.valueOf(menuItem.getId());
    }

    @Override
    public MenuItem parse(String menuItem, Locale locale) throws ParseException {
        int id = Integer.parseInt(menuItem);
        return new MenuItem(id);
    }
    
}
