/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mxv.service;

import com.mxv.pojo.MenuItem;
import java.util.List;

/**
 *
 * @author maixuanvinh
 */
public interface MenuItemService {
    List<MenuItem> getMenuItemByMenuId(int id);
    MenuItem getMenuItemById(int id);
}
