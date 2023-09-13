/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mxv.service.impl;

import com.mxv.pojo.Menu;
import com.mxv.repository.MenuRepository;
import com.mxv.service.MenuService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author maixuanvinh
 */
@Service
public class MenuServiceImpl implements MenuService{
    @Autowired
    private MenuRepository menuRepo;

    @Override
    public List<Menu> getMenuByRestaurantId(int resId) {
        return this.menuRepo.getMenuByRestaurantId(resId);
    }
}
