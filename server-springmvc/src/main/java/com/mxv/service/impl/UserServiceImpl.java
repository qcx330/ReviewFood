/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mxv.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.mxv.pojo.City;
import com.mxv.pojo.User;
import com.mxv.repository.UserRepository;
import com.mxv.service.CityService;
import com.mxv.service.UserService;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author quynh
 */
@Service("userDetailsService")
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private CityService cityService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User u = this.userRepo.getUserByUserName(userName);
        if (u == null) {
            throw new UsernameNotFoundException("Invalid");
        }
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(u.getUserRole()));
        return new org.springframework.security.core.userdetails.User(
                u.getUserName(), u.getPassword(), authorities);
    }

    @Override
    public User getUserByUn(String userName) {
        return this.userRepo.getUserByUserName(userName);
    }

    @Override
    public boolean authUser(String userName, String password) {
        return this.userRepo.authUser(userName, password);
    }

    @Override
    public User addUser(Map<String, String> params, MultipartFile avatar) {
        User u = new User();
        u.setFirstName(params.get("firstName"));
        u.setLastName(params.get("lastName"));
        u.setEmail(params.get("email"));
        u.setPhone(params.get("phone"));
        u.setUserName(params.get("userName"));
        u.setPassword(this.passwordEncoder.encode(params.get("password")));
        u.setUserRole("ROLE_USER");
        u.setTimeJoined(new Date());
        u.setCityId(this.cityService.getCityById(Integer.parseInt(params.get("cityId"))));
        if (!avatar.isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(avatar.getBytes(), 
                        ObjectUtils.asMap("resource_type", "auto"));
                u.setAvatar(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.userRepo.addUser(u);
        return u;
    }

    @Override
    public boolean addUser(User user) {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        return this.userRepo.addUser(user);
    }

    @Override
    public boolean updateUser(User user) {
        user.setFirstName(user.getFirstName());
        user.setLastName(user.getLastName());
        user.setCityId(user.getCityId());
        user.setPassword(user.getPassword());
        user.setUserRole(user.getUserRole());
        user.setUserName(user.getUserName());
        user.setPhone(user.getPhone());
        user.setAvatar(user.getAvatar());
        user.setEmail(user.getEmail());
        
        return this.userRepo.updateUser(user);
    }

    @Override
    public List<User> getUserRestaurant() {
        return this.userRepo.getUserRestaurant();
    }

    @Override
    public User getUserById(int id) {
        return this.userRepo.getUserById(id);
    }

    @Override
    public boolean checkUser(String userName) {
        return this.userRepo.checkUser(userName);
    }
}
