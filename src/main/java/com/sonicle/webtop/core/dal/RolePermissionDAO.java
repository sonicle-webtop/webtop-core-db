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

import com.sonicle.webtop.core.bol.ORolePermission;
import static com.sonicle.webtop.core.jooq.core.Sequences.SEQ_ROLES_PERMISSIONS;
import static com.sonicle.webtop.core.jooq.core.Tables.*;
import com.sonicle.webtop.core.jooq.core.tables.records.RolesPermissionsRecord;
import java.sql.Connection;
import java.util.Collection;
import java.util.List;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

/**
 *
 * @author malbinola
 */
public class RolePermissionDAO extends BaseDAO {
	private final static RolePermissionDAO INSTANCE = new RolePermissionDAO();
	public static RolePermissionDAO getInstance() {
		return INSTANCE;
	}
	
	public Long getSequence(Connection con) throws DAOException {
		DSLContext dsl = getDSL(con);
		Long nextID = dsl.nextval(SEQ_ROLES_PERMISSIONS);
		return nextID;
	}
	
	public List<ORolePermission> selectByRoleUid(Connection con, String roleUid) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select()
			.from(ROLES_PERMISSIONS)
			.where(
					ROLES_PERMISSIONS.ROLE_UID.equal(roleUid)
			)
			.orderBy(
					ROLES_PERMISSIONS.SERVICE_ID,
					ROLES_PERMISSIONS.KEY,
					ROLES_PERMISSIONS.ACTION,
					ROLES_PERMISSIONS.INSTANCE
			)
			.fetchInto(ORolePermission.class);
	}
	
	public List<ORolePermission> selectByRoleIn(Connection con, Collection<String> roleUids) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select()
			.from(ROLES_PERMISSIONS)
			.where(
					ROLES_PERMISSIONS.ROLE_UID.in(roleUids)
			)
			.orderBy(
					ROLES_PERMISSIONS.SERVICE_ID,
					ROLES_PERMISSIONS.KEY,
					ROLES_PERMISSIONS.ACTION,
					ROLES_PERMISSIONS.INSTANCE
			)
			.fetchInto(ORolePermission.class);
	}
	
	public List<ORolePermission> selectByRoleServiceKeyInstance(Connection con, String roleUid, String serviceId, String key, String instance) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select()
			.from(ROLES_PERMISSIONS)
			.where(
					ROLES_PERMISSIONS.ROLE_UID.equal(roleUid)
					.and(ROLES_PERMISSIONS.SERVICE_ID.equal(serviceId))
					.and(ROLES_PERMISSIONS.KEY.equal(key))
					.and(ROLES_PERMISSIONS.INSTANCE.equal(instance))
			)
			.fetchInto(ORolePermission.class);
	}
	
	public List<String> selectRolesByServiceKeyInstance(Connection con, String serviceId, String key, String instance) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.selectDistinct(
					ROLES_PERMISSIONS.ROLE_UID
			)
			.from(ROLES_PERMISSIONS)
			.where(
					ROLES_PERMISSIONS.SERVICE_ID.equal(serviceId)
					.and(ROLES_PERMISSIONS.KEY.equal(key))
					.and(ROLES_PERMISSIONS.INSTANCE.equal(instance))
			)
			.fetchInto(String.class);
	}
	
	public int insert(Connection con, ORolePermission item) throws DAOException {
		DSLContext dsl = getDSL(con);
		RolesPermissionsRecord record = dsl.newRecord(ROLES_PERMISSIONS, item);
		return dsl
			.insertInto(ROLES_PERMISSIONS)
			.set(record)
			.execute();
	}
	
	public int update(Connection con, ORolePermission item) throws DAOException {
		DSLContext dsl = getDSL(con);
		RolesPermissionsRecord record = dsl.newRecord(ROLES_PERMISSIONS, item);
		return dsl
			.update(ROLES_PERMISSIONS)
			.set(record)
			.where(
					ROLES_PERMISSIONS.ROLE_PERMISSION_ID.equal(item.getRolePermissionId())
			)
			.execute();
	}
	
	public int deleteById(Connection con, int id) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.delete(ROLES_PERMISSIONS)
			.where(
					ROLES_PERMISSIONS.ROLE_PERMISSION_ID.equal(id)
			)
			.execute();
	}
	
	public int deleteByRole(Connection con, String roleUid) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.delete(ROLES_PERMISSIONS)
			.where(
					ROLES_PERMISSIONS.ROLE_UID.equal(roleUid)
			)
			.execute();
	}
	
	public int deleteByServiceKeyInstance(Connection con, String serviceId, String key, String instance) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.delete(ROLES_PERMISSIONS)
			.where(
					ROLES_PERMISSIONS.SERVICE_ID.equal(serviceId)
					.and(ROLES_PERMISSIONS.KEY.equal(key))
					.and(ROLES_PERMISSIONS.INSTANCE.equal(instance))
			)
			.execute();
	}
	
	public int deleteByRoleServiceKeyActionInstance(Connection con, String roleUid, String serviceId, String key, String action, String instance) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.delete(ROLES_PERMISSIONS)
			.where(
					ROLES_PERMISSIONS.ROLE_UID.equal(roleUid)
					.and(ROLES_PERMISSIONS.SERVICE_ID.equal(serviceId))
					.and(ROLES_PERMISSIONS.KEY.equal(key))
					.and(ROLES_PERMISSIONS.ACTION.equal(action))
					.and(ROLES_PERMISSIONS.INSTANCE.equal(instance))
			)
			.execute();
	}
	
	public int deleteByDomain(Connection con, String domainId) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.delete(ROLES_PERMISSIONS)
			.where(
					ROLES_PERMISSIONS.ROLE_UID.in(
							DSL.select(
								ROLES.ROLE_UID
							)
							.from(ROLES)
							.where(
									ROLES.DOMAIN_ID.equal(domainId)
							)
					).or(ROLES_PERMISSIONS.ROLE_UID.in(
							DSL.select(
								USERS.USER_UID
							)
							.from(USERS)
							.where(
									USERS.DOMAIN_ID.equal(domainId)
							)
					))
			)
			.execute();
	}
}
