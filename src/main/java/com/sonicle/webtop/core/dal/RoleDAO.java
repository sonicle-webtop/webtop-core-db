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

import com.sonicle.webtop.core.bol.ORole;
import com.sonicle.webtop.core.bol.OUser;
import static com.sonicle.webtop.core.jooq.core.Tables.*;
import com.sonicle.webtop.core.jooq.core.tables.records.RolesRecord;
import java.sql.Connection;
import java.util.List;
import org.jooq.DSLContext;

/**
 *
 * @author malbinola
 */
public class RoleDAO extends BaseDAO {
	private final static RoleDAO INSTANCE = new RoleDAO();
	public static RoleDAO getInstance() {
		return INSTANCE;
	}
	
	public ORole selectByUid(Connection con, String uid) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select()
			.from(ROLES)
			.where(
				ROLES.ROLE_UID.equal(uid)
			)
			.fetchOneInto(ORole.class);
	}
	
	public List<ORole> selectByDomain(Connection con, String domainId) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select()
			.from(ROLES)
			.where(
				ROLES.DOMAIN_ID.equal(domainId)
			)
			.orderBy(
				ROLES.NAME
			)
			.fetchInto(ORole.class);
	}
	
	public List<ORole> selectFromGroupsByUser(Connection con, String userUid) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.selectDistinct(
				USERS.USER_UID.as("role_uid"),
				USERS.DOMAIN_ID,
				USERS.USER_ID.as("name"),
				USERS.DISPLAY_NAME.as("description")
			)
			.from(USERS_ASSOCIATIONS)
			.join(USERS)
			.on(
				USERS_ASSOCIATIONS.GROUP_UID.equal(USERS.USER_UID)
			)
			.where(
				USERS_ASSOCIATIONS.USER_UID.equal(userUid)
				.and(USERS.TYPE.equal(OUser.TYPE_GROUP))
			)
			.fetchInto(ORole.class);
	}
	
	public List<ORole> selectDirectByUser(Connection con, String userUid) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.selectDistinct(
				ROLES.ROLE_UID,
				ROLES.DOMAIN_ID,
				ROLES.NAME,
				ROLES.DESCRIPTION
			)
			.from(ROLES_ASSOCIATIONS)
			.join(ROLES)
			.on(
				ROLES_ASSOCIATIONS.ROLE_UID.equal(ROLES.ROLE_UID)
			)
			.where(
				ROLES_ASSOCIATIONS.USER_UID.equal(userUid)
			)
			.fetchInto(ORole.class);
	}
	
	public List<ORole> selectTransitiveFromGroupsByUser(Connection con, String userUid) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.selectDistinct(
				ROLES.ROLE_UID,
				ROLES.DOMAIN_ID,
				ROLES.NAME,
				ROLES.DESCRIPTION
			)
			.from(ROLES_ASSOCIATIONS)
			.join(USERS_ASSOCIATIONS)
			.on(
				ROLES_ASSOCIATIONS.USER_UID.equal(USERS_ASSOCIATIONS.GROUP_UID)
			)
			.join(ROLES)
			.on(
				ROLES_ASSOCIATIONS.ROLE_UID.equal(ROLES.ROLE_UID)
			)
			.where(
				USERS_ASSOCIATIONS.USER_UID.equal(userUid)
			)
			.fetchInto(ORole.class);
	}
	
	public int insert(Connection con, ORole item) throws DAOException {
		DSLContext dsl = getDSL(con);
		RolesRecord record = dsl.newRecord(ROLES, item);
		return dsl
			.insertInto(ROLES)
			.set(record)
			.execute();
	}
	
	public int update(Connection con, ORole item) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.update(ROLES)
			.set(ROLES.NAME, item.getName())
			.set(ROLES.DESCRIPTION, item.getDescription())
			.where(
				ROLES.ROLE_UID.equal(item.getRoleUid())
			)
			.execute();
	}
	
	public int deleteByUid(Connection con, String uid) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.delete(ROLES)
			.where(
				ROLES.ROLE_UID.equal(uid)
			)
			.execute();
	}
	
	public int deleteByDomain(Connection con, String domainId) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.delete(ROLES)
			.where(
				ROLES.DOMAIN_ID.equal(domainId)
			)
			.execute();
	}
}
