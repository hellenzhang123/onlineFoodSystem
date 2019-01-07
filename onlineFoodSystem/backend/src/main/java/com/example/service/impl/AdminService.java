package com.example.service.impl;
//package com.example.service;
//
//import java.util.ArrayList;
//import java.util.Collection;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.example.dao.AdminDao;
//import com.example.model.Admin;
//
//@Service
//public class AdminService implements UserDetailsService{
//	@Autowired  //数据库服务类
//    private AdminDao adminDao;
//
//	@Override
//	public UserDetails loadUserByUsername(String key) throws UsernameNotFoundException {
//        //SUser对应数据库中的用户表，是最终存储用户和密码的表，可自定义
//        //本例使用SUser中的email作为用户名:
//        Admin admin = adminDao.findByName(key);
//
//        if (admin == null) {
//
//            throw new UsernameNotFoundException("UserName " + admin + " not found");
//
//        }
//        System.out.println(admin);
//
//        // SecurityUser实现UserDetails并将SUser的Email映射为username
////        SecurityUser securityUser = new SecurityUser(user);
//        Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
//        if (admin.isSysAdmin()) {
//        	 authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//        } else {
//        	authorities.add(new SimpleGrantedAuthority("ROLE_SELLER"));
//        }
//       
//        return admin; 
//	}
//
//}
