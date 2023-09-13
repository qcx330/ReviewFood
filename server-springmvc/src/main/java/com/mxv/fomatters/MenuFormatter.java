/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mxv.fomatters;

import com.mxv.pojo.Menu;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author maixuanvinh
 */
public class MenuFormatter implements Formatter<Menu>{
    @Override
    public String print(Menu menu, Locale locale) {
        return String.valueOf(menu.getId());
    }

    @Override
    public Menu parse(String menu, Locale locale) throws ParseException {
        int id = Integer.parseInt(menu);
        return new Menu(id);
    }
}
