/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mxv.service.impl;

import com.mxv.pojo.Cart;
import com.mxv.repository.ReceiptRepository;
import com.mxv.service.ReceiptService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author maixuanvinh
 */
@Service
public class ReceiptServiceImpl implements ReceiptService{
    @Autowired
    private ReceiptRepository receiptRepo;

    @Override
    public boolean addReceipt(Cart cart) {
        return this.receiptRepo.addReceipt(cart);
    }
    
}
