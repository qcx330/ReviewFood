/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mxv.repository;

import com.mxv.pojo.User;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author quynh
 */
public interface UserRepository {
    User getUserByUserName(String userName);
    User getUserById(int id);
    boolean authUser(String userName, String password);
    boolean updateUser(User user);
    boolean addUser(User user);
    List<User> getUserRestaurant();
    boolean checkUser(String userName);
}
