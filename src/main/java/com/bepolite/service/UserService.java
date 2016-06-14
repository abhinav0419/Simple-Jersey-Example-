package com.bepolite.service;

import java.util.List;

import com.bepolite.model.User;

public interface UserService {
	
	public boolean saveOrUpdate(User user);
	public List<User> listUser(Integer limit);
	public User addUser(User user);
}
