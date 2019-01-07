package com.example.controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.UserDao;
import com.example.model.User;
import com.example.service.UserService;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserDao userDao;
	
	private static Logger log = LogManager.getLogger(UserController.class.getName());

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 操作结果
     * @throws AuthenticationException 错误信息
     */
	@RequestMapping(value="/user/login",method= RequestMethod.POST)
    public String getToken(String name, String password) throws AuthenticationException {
		System.out.println("name" + name);
		System.out.println("password" + password);
		System.out.println("returnValue" + userService.login(name, password));
        return userService.login(name, password);
    }
	
	@RequestMapping(value="/user/current",method= RequestMethod.GET)
    public User getinfo() throws AuthenticationException {
		
        return userService.getCurrentUser();
    }

    /**
     * 用户注册
     *
     * @param user          用户信息
     * @return 操作结果
     * @throws AuthenticationException 错误信息
     */
	@RequestMapping(value="/user/register",method= RequestMethod.POST)
    public String register(@RequestBody User user) throws AuthenticationException {
		System.out.println("user" + user);
        return userService.register(user);
    }

	@RequestMapping(value="/user/forget",method= RequestMethod.POST)
    public boolean forget(@RequestParam(value="name") String name, @RequestParam(value="question") String question,
    		@RequestParam(value="answer") String answer) throws Exception {
        User user = userService.findUser(name);
        if (user != null) {
        	if (user.getQuestion().equals(question) && user.getAnswer().equals(answer)) {
        		return true;
        	} else {
        		return false;
        	}
        } else {
        	return false;
        }
		
    }
	
	@RequestMapping(value="/user/password",method= RequestMethod.POST)
    public User modifyPassword(@RequestParam(value="name") String name, @RequestParam(value="password") String password) throws Exception {
        User user = userService.findUser(name);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(password));
        return userDao.save(user);
    }
    /**
     * 刷新密钥
     *
     * @param authorization 原密钥
     * @return 新密钥
     * @throws AuthenticationException 错误信息
     */
	@RequestMapping(value="/user/refreshToken",method= RequestMethod.GET)
    public String refreshToken(@RequestHeader String authorization) throws AuthenticationException {
        return userService.refreshToken(authorization);
    }

	@RequestMapping(value="/user/list",produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public Page<User> getUser(@RequestParam(value = "sortId", required=false) Integer sortId, 
			@RequestParam(value = "name", required=false) String name, @RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "15") Integer size) throws Exception{
		Sort sort = new Sort(Sort.Direction.DESC, "id");
	    @SuppressWarnings("deprecation")
		Pageable pageable = new PageRequest(page, size, sort);
	    Page<User> pages=userService.getUserList(pageable, name);
		return pages;
	}

	@RequestMapping(value="/user/{id}",method= RequestMethod.PUT)
	public User modifyUser(@RequestBody User user, @PathVariable(value = "id") Integer id) {
		return userService.modifyUser(user, id);
	}
}
