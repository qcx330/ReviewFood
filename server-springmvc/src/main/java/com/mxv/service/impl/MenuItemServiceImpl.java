/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mxv.service.impl;

import com.mxv.pojo.MenuItem;
import com.mxv.repository.MenuItemRepository;
import com.mxv.service.MenuItemService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author maixuanvinh
 */
@Service
public class MenuItemServiceImpl implements MenuItemService{
    @Autowired
    private MenuItemRepository menuItemRepo;

    @Override
    public List<MenuItem> getMenuItemByMenuId(int id) {
        return this.menuItemRepo.getMenuItemByMenuId(id);
    }

    @Override
    public MenuItem getMenuItemById(int id) {
        return this.menuItemRepo.getMenuItemById(id);
    }
    
}
