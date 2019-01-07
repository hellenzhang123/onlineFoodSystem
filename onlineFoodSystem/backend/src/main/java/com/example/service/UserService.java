package com.example.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.model.Store;
import com.example.model.User;

public interface UserService {
	
	public User findUser(String name) throws Exception;
	
	/**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 操作结果
     */
    public String login(String name, String password);

    /**
     * 用户注册
     *
     * @param user 用户信息
     * @return 操作结果
     */
    public String register(User user);

    /**
     * 刷新密钥
     *
     * @param oldToken 原密钥
     * @return 新密钥
     */
    public String refreshToken(String oldToken);
    
    public Page<User> getUserList(Pageable pageable, String name);
    public User modifyUser(User user, int id);
	public User getCurrentUser();
}
