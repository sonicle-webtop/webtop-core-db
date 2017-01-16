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

import com.sonicle.webtop.core.bol.AssignedRole;
import com.sonicle.webtop.core.bol.ORoleAssociation;
import static com.sonicle.webtop.core.jooq.core.Sequences.SEQ_ROLES_ASSOCIATIONS;
import static com.sonicle.webtop.core.jooq.core.Tables.*;
import com.sonicle.webtop.core.jooq.core.tables.records.RolesAssociationsRecord;
import java.sql.Connection;
import java.util.List;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

/**
 *
 * @author malbinola
 */
public class RoleAssociationDAO extends BaseDAO {
	private final static RoleAssociationDAO INSTANCE = new RoleAssociationDAO();
	public static RoleAssociationDAO getInstance() {
		return INSTANCE;
	}
	
	public Long getSequence(Connection con) throws DAOException {
		DSLContext dsl = getDSL(con);
		Long nextID = dsl.nextval(SEQ_ROLES_ASSOCIATIONS);
		return nextID;
	}
	
	public List<AssignedRole> viewAssignedByUser(Connection con, String userUid) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select(
					ROLES_ASSOCIATIONS.ROLE_ASSOCIATION_ID,
					ROLES_ASSOCIATIONS.ROLE_UID,
					ROLES.NAME.as("role_name")
			)
			.from(ROLES_ASSOCIATIONS)
			.leftOuterJoin(ROLES).on(
					ROLES_ASSOCIATIONS.ROLE_UID.equal(ROLES.ROLE_UID)
			)
			.where(
					ROLES_ASSOCIATIONS.USER_UID.equal(userUid)
			)
			.orderBy(
				ROLES.NAME
			)
			.fetchInto(AssignedRole.class);
	}
	
	public List<AssignedRole> viewAssignedByGroup(Connection con, String groupUid) throws DAOException {
		return viewAssignedByUser(con, groupUid);
	}
	
	public int insert(Connection con, ORoleAssociation item) throws DAOException {
		DSLContext dsl = getDSL(con);
		RolesAssociationsRecord record = dsl.newRecord(ROLES_ASSOCIATIONS, item);
		return dsl
			.insertInto(ROLES_ASSOCIATIONS)
			.set(record)
			.execute();
	}
	
	public int deleteById(Connection con, int id) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.delete(ROLES_ASSOCIATIONS)
			.where(
					ROLES_ASSOCIATIONS.ROLE_ASSOCIATION_ID.equal(id)
			)
			.execute();
	}
	
	public int deleteByUser(Connection con, String userUid) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.delete(ROLES_ASSOCIATIONS)
			.where(
					ROLES_ASSOCIATIONS.USER_UID.equal(userUid)
			)
			.execute();
	}
	
	public int deleteByRole(Connection con, String roleUid) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.delete(ROLES_ASSOCIATIONS)
			.where(
					ROLES_ASSOCIATIONS.ROLE_UID.equal(roleUid)
			)
			.execute();
	}
	
	public int deleteByDomain(Connection con, String domainId) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.delete(ROLES_ASSOCIATIONS)
			.where(
					ROLES_ASSOCIATIONS.ROLE_UID.in(
							DSL.select(
								ROLES.ROLE_UID
							)
							.from(ROLES)
							.where(
									ROLES.DOMAIN_ID.equal(domainId)
							)
					)
			)
			.execute();
	}
}
