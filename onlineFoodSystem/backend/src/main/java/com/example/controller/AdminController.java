//package com.example.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//import com.example.service.AdminService;
//
//@RestController
//@RequestMapping("/api")
//public class AdminController {
//	@Autowired  //数据库服务类
//    private AdminService adminService;
//
//	@PostMapping("/login")
//    @ResponseBody
//    public UserDetails login(String name,String password) {
//		return adminService.loadUserByUsername(name);
//    }
//}
