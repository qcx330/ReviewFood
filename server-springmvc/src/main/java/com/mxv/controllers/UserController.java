/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mxv.controllers;

import com.mxv.components.JwtService;
import com.mxv.pojo.User;
import com.mxv.service.UserService;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author maixuanvinh
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String registerView(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(Model model, @ModelAttribute(value = "user") User user) {
//        String errMsg = "";
//            if (this.userService.addUser(user) == true) {
//                return "redirect:/login";
//            }else
//                errMsg = "Đã có lỗi xảy ra!";
//        model.addAttribute("errMsg", errMsg);
        return "register";
    }
    
    @GetMapping("/active/user/test/{id}")
    public String update(Model model, @PathVariable(value = "id") int id)  {
        model.addAttribute("user", this.userService.getUserById(id));
        return "setActiveUser";
    }
    
    
    @RequestMapping("/active/user/")
    public String indexSetActiveUserView(Model model){
        model.addAttribute("listUser", this.userService.getUserRestaurant());
        return "listUserByRestaurant";
    }
    
    @GetMapping("/active/user/test")
    public String list(Model model){
        model.addAttribute("user", new User());
        return "setActiveUser";
    }
    
//    @PostMapping("/active/user/test")
//    public String add(@RequestBody (value = "user") @Valid User user, BindingResult rs) {
//        if (!rs.hasErrors())
//            if (this.userService.updateUser(user) == true)
//                return "redirect:/";
//        return "setActiveUser";
//    }
    
//    @PostMapping("/active/user/test")
//    @CrossOrigin
//    public ResponseEntity<String> abc(@RequestBody User user) {
//        if (this.userService.updateUser(user) == true) {       
//            return new ResponseEntity<>("ok", HttpStatus.OK);
//        }
//
//        return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
//    }
    
    
//    @GetMapping("/update/{id}")
//    public String updateView(Model model, @PathVariable(value = "id") int id){
//        model.addAllAttributes("user", this.userService.)
//    }
//    
//    @PostMapping("/update")
//    public String update(Model model, @ModelAttribute(value = "user") User user){
//        String errMsg = "";
//        if(this.userService.updateUser(user) == true)
//            return "redirect:/login";
//        else
//            errMsg = "Đã có lỗi xảy ra!";
//        model.addAttribute("errMsg", errMsg);
//        return "update";
//    }
}
