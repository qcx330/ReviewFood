/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mxv.controllers;

import com.mxv.pojo.Cart;
import com.mxv.service.ReceiptService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author maixuanvinh
 */
@RestController
@RequestMapping("/api")
public class ApiReceiptController {

    @Autowired
    private ReceiptService receiptService;

    @PostMapping("/pay/")
    @CrossOrigin
    public ResponseEntity<String> add(@RequestBody Map<String, Cart> cart) {
        for(Cart c : cart.values())
            if(this.receiptService.addReceipt(c) == false)
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
