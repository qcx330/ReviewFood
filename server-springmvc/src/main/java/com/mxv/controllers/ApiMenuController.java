/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mxv.controllers;

import com.mxv.pojo.MenuItem;
import com.mxv.service.MenuItemService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author quynh
 */
@RestController
@RequestMapping("/api")
public class ApiMenuController {
     @Autowired
    private MenuItemService menuItemService;
    
    @GetMapping("/menu/{menuId}/")
    @CrossOrigin
    public ResponseEntity<List<MenuItem>> listComments(@PathVariable(value = "menuId") int id) {
        return new ResponseEntity<>(this.menuItemService.getMenuItemByMenuId(id), HttpStatus.OK);
    }
}
