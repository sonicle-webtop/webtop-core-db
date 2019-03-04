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

import com.sonicle.webtop.core.bol.GroupUid;
import com.sonicle.webtop.core.bol.OGroup;
import com.sonicle.webtop.core.bol.OUser;
import static com.sonicle.webtop.core.jooq.core.Tables.*;
import com.sonicle.webtop.core.jooq.core.tables.Users;
import com.sonicle.webtop.core.jooq.core.tables.records.UsersRecord;
import java.sql.Connection;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.jooq.DSLContext;

/**
 *
 * @author malbinola
 */
public class GroupDAO extends BaseDAO {
	private final static GroupDAO INSTANCE = new GroupDAO();
	public static GroupDAO getInstance() {
		return INSTANCE;
	}
	
	public List<GroupUid> viewAllUids(Connection con) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select(
				USERS.DOMAIN_ID,
				USERS.USER_ID,
				USERS.USER_UID
			).from(USERS)
			.where(
				USERS.TYPE.equal(OUser.TYPE_GROUP)
			)
			.fetchInto(GroupUid.class);
	}
	
	public Map<String, OGroup> selectByDomainIn(Connection con, String domainId, Collection<String> userIds) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select(
				USERS.DOMAIN_ID,
				USERS.USER_ID,
				USERS.TYPE,
				USERS.USER_UID,
				USERS.DISPLAY_NAME
			).from(USERS)
			.where(
				USERS.DOMAIN_ID.equal(domainId)
				.and(USERS.TYPE.equal(OUser.TYPE_GROUP))
				.and(USERS.USER_ID.in(userIds))
			)
			.fetchMap(USERS.USER_ID, OGroup.class);
	}
	
	public List<OGroup> selectByDomain(Connection con, String domainId) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select(
				USERS.DOMAIN_ID,
				USERS.USER_ID,
				USERS.TYPE,
				USERS.USER_UID,
				USERS.DISPLAY_NAME
			).from(USERS)
			.where(
				USERS.DOMAIN_ID.equal(domainId)
				.and(USERS.TYPE.equal(OUser.TYPE_GROUP))
			)
			.fetchInto(OGroup.class);
	}
	
	public List<OGroup> selectByUser(Connection con, String userSid) throws DAOException {
		DSLContext dsl = getDSL(con);
		Users USERS_2 = USERS.as("users2");
		return dsl
			.select(
				USERS.DOMAIN_ID,
				USERS.USER_ID,
				USERS.TYPE,
				USERS.USER_UID,
				USERS.DISPLAY_NAME
			)
			.from(USERS_ASSOCIATIONS)
			.join(USERS).on(USERS_ASSOCIATIONS.GROUP_UID.equal(USERS.USER_UID))
			.where(
				USERS_ASSOCIATIONS.USER_UID.equal(userSid)
				.and(USERS_2.TYPE.equal(OUser.TYPE_USER))
			)
			.fetchInto(OGroup.class);
	}
	
	public List<OGroup> selectByUser(Connection con, String domainId, String userId) throws DAOException {
		DSLContext dsl = getDSL(con);
		Users USERS_2 = USERS.as("users2");
		return dsl
			.select(
				USERS.DOMAIN_ID,
				USERS.USER_ID,
				USERS.TYPE,
				USERS.USER_UID,
				USERS.DISPLAY_NAME
			)
			.from(USERS_ASSOCIATIONS)
			.join(USERS).on(USERS_ASSOCIATIONS.GROUP_UID.equal(USERS.USER_UID))
			.join(USERS_2).on(USERS_ASSOCIATIONS.USER_UID.equal(USERS_2.USER_UID))
			.where(
				USERS_2.DOMAIN_ID.equal(domainId)
				.and(USERS_2.USER_ID.equal(userId))
				.and(USERS_2.TYPE.equal(OUser.TYPE_USER))
			)
			.fetchInto(OGroup.class);
	}
	
	public OGroup selectByDomainGroup(Connection con, String domainId, String groupId) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select(
				USERS.DOMAIN_ID,
				USERS.USER_ID,
				USERS.TYPE,
				USERS.USER_UID,
				USERS.DISPLAY_NAME
			).from(USERS)
			.where(
				USERS.DOMAIN_ID.equal(domainId)
				.and(USERS.USER_ID.equal(groupId))
				.and(USERS.TYPE.equal(OUser.TYPE_GROUP))
			)
			.fetchOneInto(OGroup.class);
	}
	
	public OGroup selectByUid(Connection con, String userUid) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select(
				USERS.DOMAIN_ID,
				USERS.USER_ID,
				USERS.TYPE,
				USERS.USER_UID,
				USERS.DISPLAY_NAME
			).from(USERS)
			.where(
				USERS.USER_UID.equal(userUid)
				.and(USERS.TYPE.equal(OUser.TYPE_GROUP))
			)
			.fetchOneInto(OGroup.class);
	}
	
	/*
	public List<OGroup> selectByUser(Connection con, String domainId, String userId) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select(
				USERS.DOMAIN_ID,
				USERS.USER_ID,
				USERS.TYPE,
				USERS.UID,
				USERS.ROLE_UID,
				USERS.DISPLAY_NAME
			)
			.from(GROUPS)
			.where(GROUPS.DOMAIN_ID.in(domainId,"*")
					.and(GROUPS.GROUP_ID.in(
							dsl.select(GROUPS.GROUP_ID)
							.from(USERS_GROUPS)
							.where(USERS_GROUPS.DOMAIN_ID.in(domainId,"*")
									.and(USERS_GROUPS.USER_ID.equal(userId))
							)
					))
			)
			.fetchInto(OGroup.class);
	}
	*/
	
	public int insert(Connection con, OGroup item) throws DAOException {
		DSLContext dsl = getDSL(con);
		item.setType(OUser.TYPE_GROUP);
		UsersRecord record = dsl.newRecord(USERS, item);
		return dsl
			.insertInto(USERS)
			.set(record)
			.execute();
	}
	
	public int update(Connection con, OGroup item) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.update(USERS)
			.set(USERS.DISPLAY_NAME, item.getDisplayName())
			.where(
				USERS.DOMAIN_ID.equal(item.getDomainId())
				.and(USERS.USER_ID.equal(item.getUserId()))
				.and(USERS.TYPE.equal(OUser.TYPE_GROUP))
			)
			.execute();
	}
	
	public int deleteByDomain(Connection con, String domainId) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.delete(USERS)
			.where(
					USERS.DOMAIN_ID.equal(domainId)
					.and(USERS.TYPE.equal(OUser.TYPE_GROUP))
			)
			.execute();
	}
	
	public int deleteByDomainGroup(Connection con, String domainId, String groupId) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.delete(USERS)
			.where(
					USERS.DOMAIN_ID.equal(domainId)
					.and(USERS.USER_ID.equal(groupId))
					.and(USERS.TYPE.equal(OUser.TYPE_GROUP))
			)
			.execute();
	}
	
	public int deleteByUserUid(Connection con, String userUid) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.delete(USERS)
			.where(
					USERS.USER_UID.equal(userUid)
					.and(USERS.TYPE.equal(OUser.TYPE_GROUP))
			)
			.execute();
	}
}
