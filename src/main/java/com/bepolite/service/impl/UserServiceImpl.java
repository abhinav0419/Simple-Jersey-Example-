package com.bepolite.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bepolite.dao.UserDAO;
import com.bepolite.model.User;
import com.bepolite.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDAO;
	

	@Override
	public boolean saveOrUpdate(User user) {
		return userDAO.saveOrUpdate(user);
	}

	@Override
	public List<User> listUser(Integer limit) {
		return userDAO.listUser(limit);
	}

	@Override
	public User addUser(User user) {
		return userDAO.addUser(user);
	}

	
}
