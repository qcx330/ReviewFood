/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mxv.service;

import com.mxv.pojo.User;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author quynh
 */
public interface UserService extends UserDetailsService {
    User getUserByUn(String username);
    User getUserById(int id);
    boolean authUser(String username, String password);
    boolean addUser(User user);
    User addUser(Map<String, String> params, MultipartFile avatar);
    boolean updateUser(User user);
    List<User> getUserRestaurant();
    boolean checkUser(String userName);
}
