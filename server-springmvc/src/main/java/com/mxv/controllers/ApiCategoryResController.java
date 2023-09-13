/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mxv.controllers;

import com.mxv.pojo.CategoryRes;
import com.mxv.service.CategoryResService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author maixuanvinh
 */
@RestController
@RequestMapping("/api")
public class ApiCategoryResController {
    @Autowired
    private CategoryResService categoryResService;
    
    @GetMapping("/categoryRes/")
    @CrossOrigin
    public ResponseEntity<List<CategoryRes>> list() {
        return new ResponseEntity<>(this.categoryResService.getCategoryRes(), HttpStatus.OK);
    }
}
