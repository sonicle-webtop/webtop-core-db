/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sonicle.webtop.core.dal;

import com.sonicle.webtop.core.bol.OUser;
import static com.sonicle.webtop.core.jooq.Tables.*;
import com.sonicle.webtop.core.jooq.tables.records.UsersRecord;
import java.sql.Connection;
import java.util.List;
import org.jooq.DSLContext;
import org.jooq.exception.DataAccessException;

/**
 *
 * @author gbulfon
 */
public class UserDAO extends BaseDAO {
	
	private final static UserDAO INSTANCE = new UserDAO();
	public static UserDAO getInstance() {
		return INSTANCE;
	}
	
	public List<OUser> selectAll(Connection con) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select()
			.from(USERS)
			.fetchInto(OUser.class);
	}
	
	public OUser selectByDomainUser(Connection con, String domainId, String userId) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select()
			.from(USERS)
			.where(USERS.DOMAIN_ID.equal(domainId)
					.and(USERS.USER_ID.equal(userId))
			)
			.fetchOneInto(OUser.class);
	}
	
	public int insert(Connection con, OUser item) throws DAOException {
		DSLContext dsl = getDSL(con);
		UsersRecord record = dsl.newRecord(USERS, item);
		return dsl
			.insertInto(USERS)
			.set(record)
			.execute();
	}
	
	public int update(Connection con, OUser item) throws DAOException {
		DSLContext dsl = getDSL(con);
		UsersRecord record = dsl.newRecord(USERS, item);
		return dsl
			.update(USERS)
			.set(record)
			.where(USERS.DOMAIN_ID.equal(item.getDomainId())
					.and(USERS.USER_ID.equal(item.getUserId()))
			)
			.execute();
	}
	
	public int updateSecretByDomainUser(Connection con, String domainId, String userId, String secret) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.update(USERS)
			.set(USERS.SECRET, secret)
			.where(USERS.DOMAIN_ID.equal(domainId)
					.and(USERS.USER_ID.equal(userId))
			)
			.execute();
	}
	
	public int deleteByDomainUser(Connection con, String domainId, String userId) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.delete(USERS)
			.where(USERS.DOMAIN_ID.equal(domainId)
					.and(USERS.USER_ID.equal(userId))
			)
			.execute();
	}
}
