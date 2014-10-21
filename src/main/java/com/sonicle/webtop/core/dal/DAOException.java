/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sonicle.webtop.core.dal;

import org.jooq.exception.DataAccessException;

/**
 *
 * @author malbinola
 */
public class DAOException extends DataAccessException {
	
	public DAOException(String message) {
		super(message);
	}
	
	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}
}
