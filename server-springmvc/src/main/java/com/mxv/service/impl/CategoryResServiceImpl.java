/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mxv.service.impl;

import com.mxv.pojo.CategoryRes;
import com.mxv.repository.CategoryResRepository;
import com.mxv.service.CategoryResService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author maixuanvinh
 */
@Service
public class CategoryResServiceImpl implements CategoryResService{
    @Autowired
    private CategoryResRepository categoryResRepo;

    @Override
    public List<CategoryRes> getCategoryRes() {
        return this.categoryResRepo.getCategoryRes();
    }   

    @Override
    public CategoryRes getCateResById(int id) {
        return this.categoryResRepo.getCateResById(id);
    }
}
