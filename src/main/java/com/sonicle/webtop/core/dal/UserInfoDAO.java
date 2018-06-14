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

import com.sonicle.webtop.core.bol.OUserInfo;
import com.sonicle.webtop.core.bol.UserId;
import static com.sonicle.webtop.core.jooq.core.Tables.USERS_INFO;
import com.sonicle.webtop.core.jooq.core.tables.records.UsersInfoRecord;
import java.sql.Connection;
import java.util.Collection;
import java.util.List;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

/**
 *
 * @author malbinola
 */
public class UserInfoDAO extends BaseDAO {
	private final static UserInfoDAO INSTANCE = new UserInfoDAO();
	public static UserInfoDAO getInstance() {
		return INSTANCE;
	}
	
	public OUserInfo selectByDomainUser(Connection con, String domainId, String userId) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select()
			.from(USERS_INFO)
			.where(
				USERS_INFO.DOMAIN_ID.equal(domainId)
				.and(USERS_INFO.USER_ID.equal(userId))
			)
			.fetchOneInto(OUserInfo.class);
	}
	
	public List<UserId> viewByEmail(Connection con, String email) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select(
				USERS_INFO.DOMAIN_ID,
				USERS_INFO.USER_ID
			).from(USERS_INFO)
			.where(
				USERS_INFO.EMAIL.equal(email)
			)
			.fetchInto(UserId.class);
	}
	
	public int insert(Connection con, String domainId, String userId) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.insertInto(USERS_INFO,
					USERS_INFO.DOMAIN_ID,
					USERS_INFO.USER_ID
			)
			.values(domainId, userId)
			.execute();
	}
	
	public int insert(Connection con, OUserInfo item) throws DAOException {
		DSLContext dsl = getDSL(con);
		UsersInfoRecord record = dsl.newRecord(USERS_INFO, item);
		return dsl
			.insertInto(USERS_INFO)
			.set(record)
			.execute();
	}
	
	public int update(Connection con, OUserInfo item) throws DAOException {
		DSLContext dsl = getDSL(con);
		UsersInfoRecord record = dsl.newRecord(USERS_INFO, item);
		return dsl
			.update(USERS_INFO)
			.set(record)
			.where(
				USERS_INFO.DOMAIN_ID.equal(item.getDomainId())
				.and(USERS_INFO.USER_ID.equal(item.getUserId()))
			)
			.execute();
	}
	
	public int updateEmailDomainByProfiles(Connection con, String domainId, Collection<String> userIds, String domainSuffix) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.update(USERS_INFO)
			.set(USERS_INFO.EMAIL, DSL.concat(DSL.substring(USERS_INFO.EMAIL, DSL.val(0), DSL.position(USERS_INFO.EMAIL, "@")), domainSuffix))
			.where(
				USERS_INFO.DOMAIN_ID.equal(domainId)
				.and(USERS_INFO.USER_ID.in(userIds))
			)
			.execute();
	}
	
	public int updateFirstLastName(Connection con, OUserInfo item) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.update(USERS_INFO)
			.set(USERS_INFO.FIRST_NAME, item.getFirstName())
			.set(USERS_INFO.LAST_NAME, item.getLastName())
			.where(
				USERS_INFO.DOMAIN_ID.equal(item.getDomainId())
				.and(USERS_INFO.USER_ID.equal(item.getUserId()))
			)
			.execute();
	}
	
	public int deleteByDomain(Connection con, String domainId) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.delete(USERS_INFO)
			.where(
					USERS_INFO.DOMAIN_ID.equal(domainId)
			)
			.execute();
	}
	
	public int deleteByDomainUser(Connection con, String domainId, String userId) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.delete(USERS_INFO)
			.where(
				USERS_INFO.DOMAIN_ID.equal(domainId)
				.and(USERS_INFO.USER_ID.equal(userId))
			)
			.execute();
	}
}
