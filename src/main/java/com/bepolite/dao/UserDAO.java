package com.bepolite.dao;

import java.util.List;

import com.bepolite.model.User;

/**
 * 
 * @author AnhHoang
 *
 */
public interface UserDAO {
	
	public boolean saveOrUpdate(User user);
	
	public List<User> listUser(Integer limit);
	public User addUser(User user);
}
