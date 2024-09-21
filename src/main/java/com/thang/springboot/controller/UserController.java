package com.thang.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thang.springboot.dto.UserDTO;
import com.thang.springboot.entity.User;
import com.thang.springboot.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {
	 @Autowired
	 private UserService userService;
	 
	 @PostMapping("/add")
	 public String addUser(@RequestBody User user) {
	        userService.addUser(user);

	        return "success add user";
	    }
	 
	 @GetMapping("/getList")
	 public List<User> getUsers(HttpSession session) {
//		 phai login moi xem duoc danh sach
		 if(session.getAttribute("USERNAME")!=null) {
	        return userService.getUsers();
		 }
		 return null;
	    }
	 
	 @GetMapping("/getById")
	 public User getUser(@RequestParam Integer id) {
	        return userService.getUser(id);
	    }
	 
	 @PutMapping("/update/{id}")
	 public ResponseEntity<Void> updateUser(@PathVariable Integer id, @RequestBody User user) {
	        userService.updateUser(id, user);

	        return ResponseEntity.noContent().build();
	    }
	 
	 @DeleteMapping("delete/{id}")
	    public ResponseEntity<Void> deleteUser(@PathVariable Integer id){
	        userService.deleteUser(id);
	        return ResponseEntity.noContent().build();
	    }
	 
	 @PatchMapping("/update-name/{id}")
	    public ResponseEntity<Void> updateName(@PathVariable Integer id, @RequestBody UserDTO userDTO){
	        userService.updateName(id, userDTO);

	        return ResponseEntity.noContent().build();
	    }
	 @GetMapping("/login")
	 public String showLogin() {
		 return "trang login";
	 }
	 
	 @PostMapping("/checkLogin")
	 public String checkLogin(@RequestParam("username")String name, @RequestParam("password")
	 Integer password, HttpSession session) {
		 if(userService.checkLogin(name, password)){
			 session.setAttribute("USERNAME", name);
			 return "login thanh cong";
		 }else {
			 return"login that bai";
		 }
	 }
	 @GetMapping("/logout")
	 public String logout (HttpSession session) {
		 session.removeAttribute("USERNAME");
		 return "logout thanh cong";
	 }
	 
	 
}










