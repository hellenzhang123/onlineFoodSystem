package com.example.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
	public User findByName(String name);
	public Page<User> findAll(Pageable pageable);
	public Page<User> findByNameLike(String name, Pageable pageable);
	public User findById(int id);
}
