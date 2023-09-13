/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mxv.fomatters;

import com.mxv.pojo.InOrder;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author maixuanvinh
 */
public class InOrderFormatter implements Formatter<InOrder>{

    @Override
    public String print(InOrder inOrder, Locale locale) {
        return String.valueOf(inOrder.getId());
    }

    @Override
    public InOrder parse(String inOder, Locale locale) throws ParseException {
        int id = Integer.parseInt(inOder);
        return new InOrder(id);
    }
    
}
