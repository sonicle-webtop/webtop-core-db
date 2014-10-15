/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sonicle.webtop.core.dal;

import java.sql.Connection;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

/**
 *
 * @author malbinola
 */
public class BaseDAO {
	
	public SQLDialect getDialect(Connection con) {
		return SQLDialect.POSTGRES;
	}
	
	public DSLContext getDSL(Connection con) {
		return DSL.using(con, getDialect(con));
	}
}
