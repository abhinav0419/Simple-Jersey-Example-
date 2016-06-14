package com.bepolite.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.bepolite.dao.UserDAO;
import com.bepolite.model.User;

/**
 * 
 * @author AnhHoang
 *
 */

@Repository
public class UserDAOImpl extends BaseDao<User> implements UserDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<User> listUser(Integer limit) {
		Session session 	= openSession(sessionFactory);
		List<User> results 	= new ArrayList<User>();
		Criteria criteria = session.createCriteria(User.class);
		criteria.setMaxResults(limit);
		results = criteria.list();
		dispose(session);
		return results;
	}

	@Override
	public User addUser(User user) {
		// TODO Auto-generated method stub
		Session session=openSession(sessionFactory);
		session.save(user);
		dispose(session);
		return null;
	}

}
