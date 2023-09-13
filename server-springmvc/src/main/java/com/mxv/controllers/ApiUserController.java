/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mxv.controllers;

import com.mxv.components.JwtService;
import com.mxv.pojo.User;
import com.mxv.service.UserService;
import java.security.Principal;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author maixuanvinh
 */
@RestController
@RequestMapping("/api")
public class ApiUserController {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;
    
//    @PostMapping(path = "/active/user/test",produces =  MediaType.APPLICATION_JSON_VALUE)
//    @PutMapping("/active/user/test")
//    @CrossOrigin
//    public ResponseEntity<String> abc(@RequestBody User user) {
//        if (this.userService.updateUser(user) == true) {       
//            return new ResponseEntity<>("ok", HttpStatus.OK);
//        }
//
//        return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
//    }
    
    @GetMapping("/user/{userId}")
    @CrossOrigin
    public ResponseEntity<User> listComments(@PathVariable(value = "userId") int id) {
        return new ResponseEntity<>(this.userService.getUserById(id), HttpStatus.OK);
    }
    

    @PostMapping("/login/")
    @CrossOrigin
    public ResponseEntity<String> login(@RequestBody User user) {
        if (this.userService.authUser(user.getUserName(), user.getPassword()) == true) {
            String token = this.jwtService.generateTokenLogin(user.getUserName());       
            return new ResponseEntity<>(token, HttpStatus.OK);
        }

        return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/test/")
    @CrossOrigin(origins = {"127.0.0.1:5500"})
    public ResponseEntity<String> test(Principal pricipal) {
        return new ResponseEntity<>("SUCCESSFUL", HttpStatus.OK);
    }
    
//    consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, 
//            produces = {MediaType.APPLICATION_JSON_VALUE})
    @PostMapping(path = "/users/", 
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, 
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @CrossOrigin
    public ResponseEntity<User> addUser(@RequestParam Map<String, String> params, @RequestPart("avatar") MultipartFile avatar) {
//        if(this.userService.addUser(user))
//            return new ResponseEntity<>(HttpStatus.CREATED);
//        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        User user = this.userService.addUser(params, avatar);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
        
    }
    
    @GetMapping(path = "/current-user/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<User> details(Principal user) {
        User u = this.userService.getUserByUn(user.getName());
        return new ResponseEntity<>(u, HttpStatus.OK);
    }
}
