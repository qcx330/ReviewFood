/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mxv.fomatters;

import com.mxv.pojo.CategoryRes;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author maixuanvinh
 */
public class CategoryResFormatter implements Formatter<CategoryRes> {

    @Override
    public String print(CategoryRes cateRes, Locale locale) {
        return String.valueOf(cateRes.getId());
    }

    @Override
    public CategoryRes parse(String cateRes, Locale locale) throws ParseException {
        int id = Integer.parseInt(cateRes);
        return new CategoryRes(id);
    }
    
}
