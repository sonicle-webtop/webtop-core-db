/*
 * webtop-core-db is a library developed by Sonicle S.r.l.
 * Copyright (C) 2014 Sonicle S.r.l.
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Affero General Public License version 3 as published by
 * the Free Software Foundation with the addition of the following permission
 * added to Section 15 as permitted in Section 7(a): FOR ANY PART OF THE COVERED
 * WORK IN WHICH THE COPYRIGHT IS OWNED BY SONICLE, SONICLE DISCLAIMS THE
 * WARRANTY OF NON INFRINGEMENT OF THIRD PARTY RIGHTS.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program; if not, see http://www.gnu.org/licenses or write to
 * the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301 USA.
 *
 * You can contact Sonicle S.r.l. at email address sonicle@sonicle.com
 *
 * The interactive user interfaces in modified source and object code versions
 * of this program must display Appropriate Legal Notices, as required under
 * Section 5 of the GNU Affero General Public License version 3.
 *
 * In accordance with Section 7(b) of the GNU Affero General Public License
 * version 3, these Appropriate Legal Notices must retain the display of the
 * Sonicle logo and Sonicle copyright notice. If the display of the logo is not
 * reasonably feasible for technical reasons, the Appropriate Legal Notices must
 * display the words "Copyright (C) 2014 Sonicle S.r.l.".
 */
package com.sonicle.webtop.core.dal;

import com.sonicle.webtop.core.bol.OUser;
import com.sonicle.webtop.core.bol.UserUid;
import static com.sonicle.webtop.core.jooq.Tables.*;
import com.sonicle.webtop.core.jooq.tables.records.UsersRecord;
import java.sql.Connection;
import java.util.List;
import org.jooq.DSLContext;

/**
 *
 * @author malbinola
 */
public class UserDAO extends BaseDAO {
	private final static UserDAO INSTANCE = new UserDAO();
	public static UserDAO getInstance() {
		return INSTANCE;
	}
	
	public List<OUser> selectAll(Connection con) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select(
				USERS.DOMAIN_ID,
				USERS.USER_ID,
				USERS.TYPE,
				USERS.USER_UID,
				USERS.ROLE_UID,
				USERS.PASSWORD_TYPE,
				USERS.PASSWORD,
				USERS.SECRET,
				USERS.DISPLAY_NAME,
				USERS.LANGUAGE_TAG,
				USERS.TIMEZONE
			).from(USERS)
			.where(
				USERS.TYPE.equal(OUser.USER_TYPE)
			)
			.fetchInto(OUser.class);
	}
	
	public List<UserUid> selectAllUids(Connection con) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select(
				USERS.DOMAIN_ID,
				USERS.USER_ID,
				USERS.USER_UID,
				USERS.ROLE_UID
			).from(USERS)
			.where(
				USERS.TYPE.equal(OUser.USER_TYPE)
			)
			.fetchInto(UserUid.class);
	}
	
	public List<OUser> selectByDomain(Connection con, String domainId) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select(
				USERS.DOMAIN_ID,
				USERS.USER_ID,
				USERS.TYPE,
				USERS.USER_UID,
				USERS.ROLE_UID,
				USERS.PASSWORD_TYPE,
				USERS.PASSWORD,
				USERS.SECRET,
				USERS.DISPLAY_NAME,
				USERS.LANGUAGE_TAG,
				USERS.TIMEZONE
			).from(USERS)
			.where(
				USERS.DOMAIN_ID.equal(domainId)
				.and(USERS.TYPE.equal(OUser.USER_TYPE))
			)
			.fetchInto(OUser.class);
	}
	
	public OUser selectByDomainUser(Connection con, String domainId, String userId) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select(
				USERS.DOMAIN_ID,
				USERS.USER_ID,
				USERS.TYPE,
				USERS.USER_UID,
				USERS.ROLE_UID,
				USERS.PASSWORD_TYPE,
				USERS.PASSWORD,
				USERS.SECRET,
				USERS.DISPLAY_NAME,
				USERS.LANGUAGE_TAG,
				USERS.TIMEZONE
			).from(USERS)
			.where(
				USERS.DOMAIN_ID.equal(domainId)
				.and(USERS.USER_ID.equal(userId))
				.and(USERS.TYPE.equal(OUser.USER_TYPE))
			)
			.fetchOneInto(OUser.class);
	}
	
	public OUser selectByUid(Connection con, String userUid) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select(
				USERS.DOMAIN_ID,
				USERS.USER_ID,
				USERS.TYPE,
				USERS.USER_UID,
				USERS.ROLE_UID,
				USERS.PASSWORD_TYPE,
				USERS.PASSWORD,
				USERS.SECRET,
				USERS.DISPLAY_NAME,
				USERS.LANGUAGE_TAG,
				USERS.TIMEZONE
			).from(USERS)
			.where(
				USERS.TYPE.equal(OUser.USER_TYPE)
				.and(USERS.USER_UID.equal(userUid))
			)
			.fetchOneInto(OUser.class);
	}
	
	public int insert(Connection con, OUser item) throws DAOException {
		DSLContext dsl = getDSL(con);
		item.setType(OUser.USER_TYPE);
		UsersRecord record = dsl.newRecord(USERS, item);
		return dsl
			.insertInto(USERS)
			.set(record)
			.execute();
	}
	
	public int update(Connection con, OUser item) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.update(USERS)
			.set(USERS.DISPLAY_NAME, item.getDisplayName())
			.set(USERS.LANGUAGE_TAG, item.getLanguageTag())
			.set(USERS.TIMEZONE, item.getTimezone())
			.where(
				USERS.DOMAIN_ID.equal(item.getDomainId())
				.and(USERS.USER_ID.equal(item.getUserId()))
				.and(USERS.TYPE.equal(OUser.USER_TYPE))
			)
			.execute();
	}
	
	public int updateSecretByDomainUser(Connection con, String domainId, String userId, String secret) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.update(USERS)
			.set(USERS.SECRET, secret)
			.where(
					USERS.DOMAIN_ID.equal(domainId)
					.and(USERS.USER_ID.equal(userId))
					.and(USERS.TYPE.equal(OUser.USER_TYPE))
			)
			.execute();
	}
	
	public int deleteByDomain(Connection con, String domainId) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.delete(USERS)
			.where(
					USERS.DOMAIN_ID.equal(domainId)
					.and(USERS.TYPE.equal(OUser.USER_TYPE))
			)
			.execute();
	}
	
	public int deleteByDomainUser(Connection con, String domainId, String userId) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.delete(USERS)
			.where(
					USERS.DOMAIN_ID.equal(domainId)
					.and(USERS.USER_ID.equal(userId))
					.and(USERS.TYPE.equal(OUser.USER_TYPE))
			)
			.execute();
	}
	
	public int deleteByUid(Connection con, String userUid) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.delete(USERS)
			.where(
					USERS.USER_UID.equal(userUid)
					.and(USERS.TYPE.equal(OUser.USER_TYPE))
			)
			.execute();
	}
}
