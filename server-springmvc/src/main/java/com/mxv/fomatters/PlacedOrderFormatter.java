/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mxv.fomatters;

import com.mxv.pojo.PlacedOrder;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author maixuanvinh
 */
public class PlacedOrderFormatter implements Formatter<PlacedOrder>{

    @Override
    public String print(PlacedOrder placedOrder, Locale locale) {
        return String.valueOf(placedOrder.getId());
    }

    @Override
    public PlacedOrder parse(String placedOrder, Locale locale) throws ParseException {
        int id = Integer.parseInt(placedOrder);
        return new PlacedOrder(id);
    }
    
}
