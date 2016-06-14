package com.bepolite.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.SessionFactoryUtils;

import com.bepolite.dao.exception.DAOException;

/**
 * 
 * @author AnhHoang
 *
 */

public class BaseDao<T> {
	@Autowired
	protected SessionFactory sessionFactory;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseDao.class);
	
	/**
	 * This method is used to save or update T.
	 * @param t
	 * @return
	 */
	public boolean saveOrUpdateList(List<T> list) {
		Transaction tx 	= null;
		Session session = null;
		boolean success = false;
		try {
			session 	= openSession(sessionFactory);
			tx 			= session.beginTransaction();
			for (T t : list) {
				session.saveOrUpdate(t);
			}
			success 	= true;
		} catch (Exception ex) {
			LOGGER.error("Exception: " + ex);
			throw new DAOException(ex);
		} finally {
			commitOrRollback(tx, success);
			dispose(session);
		}
		
		return success;
	}
	
	/**
	 * This method is used to save or update T.
	 * @param t
	 * @return
	 */
	public boolean saveOrUpdate(T t) {
		Transaction tx 	= null;
		Session session = null;
		boolean success = false;
		try {
			session 	= openSession(sessionFactory);
			tx 			= session.beginTransaction();
			session.saveOrUpdate(t);
			success 	= true;
		} catch (Exception ex) {
			LOGGER.error("Exception: " + ex);
			throw new DAOException(ex);
		} finally {
			commitOrRollback(tx, success);
			dispose(session);
		}
		return success;
	}
	
	/**
	 * Commits or rolls back a transaction
	 * 
	 * @param tx
	 *            the given {@link Transaction transaction}
	 * @param success
	 *            indicates whether the process succeeded or failed
	 */
	protected static void commitOrRollback(final Transaction tx, final boolean success) {
		if (tx != null) {
			try {
				// was the process successful?
				if (success) {
					tx.commit();
				} else {
					tx.rollback();
				}
			} catch (Exception e) {
				tx.rollback();
				LOGGER.debug(String.format("%s failure", (success ? "Commit" : "Rollback")), e);
				throw e;
			}
		}
	}

	protected static Session openSession(final SessionFactory sessionFactory) {
		return sessionFactory.openSession();
	}

	public static void dispose(final Session session) {
		if (session != null) {
			SessionFactoryUtils.closeSession(session);
		}
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}
