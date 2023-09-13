/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mxv.repository.impl;

import com.mxv.pojo.Cart;
import com.mxv.pojo.InOrder;
import com.mxv.pojo.MenuItem;
import com.mxv.pojo.Offer;
import com.mxv.pojo.PlacedOrder;
import com.mxv.pojo.Restaurant;
import com.mxv.pojo.User;
import com.mxv.repository.MenuItemRepository;
import com.mxv.repository.ReceiptRepository;
import com.mxv.repository.UserRepository;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author maixuanvinh
 */
@Repository
@Transactional
public class ReceiptRepositoryImpl implements ReceiptRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private MenuItemRepository menuItemRepo;

    @Override
    public boolean addReceipt(Cart cart) {
        Session s = this.factory.getObject().getCurrentSession();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User u = this.userRepo.getUserByUserName(authentication.getName());
        try {
            PlacedOrder order = new PlacedOrder();
            order.setUserId(u);
            order.setOrderTime(new Date());
            MenuItem menuItem = null;
            menuItem = this.menuItemRepo.getMenuItemById(cart.getId());
            order.setRestaurantId(menuItem.getMenuId().getRestaurantId());
            if (menuItem == null) {
                return false;
            }
            s.save(order);

            addReceiptDetail(cart, order, menuItem);
            return true;
        } catch (HibernateException ex) {
            return false;
        }
    }

    public void addReceiptDetail(Cart cart, PlacedOrder placedOrder, MenuItem menuItem) {
        Session s = this.factory.getObject().getCurrentSession();
        InOrder inOrder = new InOrder();
        inOrder.setMenuItemId(menuItem);
        inOrder.setPlacedOrderId(placedOrder);
        s.save(inOrder);
    }
}
