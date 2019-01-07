package com.example.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.dao.UserDao;
import com.example.model.JwtUser;
import com.example.model.User;
import com.example.service.UserService;
import com.example.util.JwtTokenUtil;

@Service
public class UserServiceImpl implements UserService {
	private UserDao userDao;
	private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private JwtTokenUtil jwtTokenUtil;
    

    @Autowired
    public UserServiceImpl(
            AuthenticationManager authenticationManager,
            UserDetailsService userDetailsService,
            JwtTokenUtil jwtTokenUtil,
            UserDao userDao) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDao = userDao;
    }

    @Override
    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return jwtTokenUtil.generateToken(userDetails);
    }

    @Override
    public String register(User user) {
        String username = user.getName();
        if (userDao.findByName(username) != null) {
            return "用户已存在";
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = user.getPassword();
        user.setPassword(encoder.encode(rawPassword));
        List<String> roles = new ArrayList<>();
        roles.add("ROLE_USER");
//        user.setRoles(roles);
        userDao.save(user);
        return "success";
    }

    @Override
    public String refreshToken(String oldToken) {
        String token = oldToken.substring("Bearer ".length());
        if (!jwtTokenUtil.isTokenExpired(token)) {
            return jwtTokenUtil.refreshToken(token);
        }
        return "error";
    }
	

	@Override
	public User findUser(String name) throws Exception {
		System.out.println(userDao.findByName(name));
		return userDao.findByName(name);
	}

	public Page<User> getUserList(Pageable pageable, String name) {
		if (name == null || name =="") {
			return userDao.findAll(pageable);
		} else {
			return userDao.findByNameLike(name, pageable);
		}
	}

	@Override
	public User modifyUser(User user, int id) {
		User findStore = userDao.findById(id);
		userDao.findById(id);
		if (findStore == null) {
			//抛异常
			return null;
		} else {
			user.setId(id);
			return userDao.save(user);
		}
	}

	@Override
	public User getCurrentUser() {
		JwtUser juser = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userDao.findByName(juser.getUsername());
		return user;
	}

}
