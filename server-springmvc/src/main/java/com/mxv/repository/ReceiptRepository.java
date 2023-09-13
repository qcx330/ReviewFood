/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mxv.repository;

import com.mxv.pojo.Cart;
import java.util.List;
import java.util.Map;

/**
 *
 * @author maixuanvinh
 */
public interface ReceiptRepository {
    boolean addReceipt(Cart cart);
}
