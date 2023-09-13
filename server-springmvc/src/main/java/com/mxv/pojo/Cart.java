/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mxv.pojo;

import lombok.Data;

/**
 *
 * @author maixuanvinh
 */
@Data
public class Cart {
    private int id;
    private String name;
    private int quantity;
    private double unitPrice;
}
