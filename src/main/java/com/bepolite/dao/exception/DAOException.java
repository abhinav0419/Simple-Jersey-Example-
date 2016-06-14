package com.bepolite.dao.exception;

import java.sql.SQLException;

import org.hibernate.exception.ConstraintViolationException;

/**
 * 
 * @author AnhHoang
 *
 */

@SuppressWarnings("serial")
public class DAOException extends RuntimeException {

	public DAOException(final Throwable cause) {
		super(cause);
	}

	public DAOException(final String message) {
		super(message);
	}

	public DAOException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public boolean isFkFailureOnUpdateOrDelete() {
		if (getCause() instanceof ConstraintViolationException) {
			SQLException exception = ((ConstraintViolationException) getCause()).getSQLException();
			if ("23000".equals(exception.getSQLState()) && (exception.getErrorCode() == 1451)) {
				return true;
			}
		}
		return false;
	}

	public boolean isUniqueContraintViolation() {
		if (getCause() instanceof ConstraintViolationException) {
			SQLException exception = ((ConstraintViolationException) getCause()).getSQLException();
			if ("23000".equals(exception.getSQLState()) && (exception.getErrorCode() == 1062)) {
				return true;
			}
		}
		return false;
	}
}
