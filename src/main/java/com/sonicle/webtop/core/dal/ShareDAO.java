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

import com.sonicle.webtop.core.bol.OShare;
import static com.sonicle.webtop.core.jooq.core.Sequences.SEQ_SHARES;
import static com.sonicle.webtop.core.jooq.core.Tables.*;
import com.sonicle.webtop.core.jooq.core.tables.records.SharesRecord;
import java.sql.Connection;
import java.util.Collection;
import java.util.List;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

/**
 *
 * @author malbinola
 */
public class ShareDAO extends BaseDAO {
	private final static ShareDAO INSTANCE = new ShareDAO();
	public static ShareDAO getInstance() {
		return INSTANCE;
	}
	
	public Long getSequence(Connection con) throws DAOException {
		DSLContext dsl = getDSL(con);
		Long nextID = dsl.nextval(SEQ_SHARES);
		return nextID;
	}
	
	public List<String> viewOriginByRoleServiceKey(Connection con, Collection<String> targetRoleUids, String serviceId, String shareKey, Collection<String> permissionKeys) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.selectDistinct(
				SHARES.USER_UID
			)
			.from(SHARES)
			.where(
					SHARES.SHARE_ID.in(
						DSL.selectDistinct(
								ROLES_PERMISSIONS.INSTANCE.cast(Integer.class)
						)
						.from(ROLES_PERMISSIONS)
						.where(
								ROLES_PERMISSIONS.ROLE_UID.in(targetRoleUids)
								.and(ROLES_PERMISSIONS.SERVICE_ID.equal(serviceId))
								.and(ROLES_PERMISSIONS.KEY.in(permissionKeys))
						)
					)
					.and(SHARES.SERVICE_ID.equal(serviceId))
					.and(SHARES.KEY.equal(shareKey))
			)
			.fetchInto(String.class);
	}
	
	public List<OShare> selectByRoleServiceKey___(Connection con, Collection<String> targetRoleUids, String serviceId, String shareKey, String permissionKey) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select(
				SHARES.fields()
			)
			.from(SHARES)
			.where(
					SHARES.SHARE_ID.in(
						DSL.selectDistinct(
								ROLES_PERMISSIONS.INSTANCE.cast(Integer.class)
						)
						.from(ROLES_PERMISSIONS)
						.where(
								ROLES_PERMISSIONS.ROLE_UID.in(targetRoleUids)
								.and(ROLES_PERMISSIONS.SERVICE_ID.equal(serviceId))
								.and(ROLES_PERMISSIONS.KEY.equal(permissionKey))
						)
					)
					.and(SHARES.SERVICE_ID.equal(serviceId))
					.and(SHARES.KEY.equal(shareKey))
			)
			.fetchInto(OShare.class);
	}
	
	public List<OShare> selectByUserServiceKey(Connection con, String userUid, String serviceId, String shareKey) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select(
				SHARES.fields()
			)
			.from(SHARES)
			.where(
					SHARES.USER_UID.equal(userUid)
					.and(SHARES.SERVICE_ID.equal(serviceId))
					.and(SHARES.KEY.equal(shareKey))
			)
			.orderBy(
					SHARES.INSTANCE.asc()
			)
			.fetchInto(OShare.class);
	}
	
	public OShare selectByUserServiceKeyInstance(Connection con, String userUid, String serviceId, String shareKey, String instance) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select(
				SHARES.fields()
			)
			.from(SHARES)
			.where(
					SHARES.USER_UID.equal(userUid)
					.and(SHARES.SERVICE_ID.equal(serviceId))
					.and(SHARES.KEY.equal(shareKey))
					.and(SHARES.INSTANCE.equal(instance))
			)
			.fetchOneInto(OShare.class);
	}
	
	public OShare selectById(Connection con, int shareId) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select(
				SHARES.fields()
			)
			.from(SHARES)
			.where(
					SHARES.SHARE_ID.equal(shareId)
			)
			.fetchOneInto(OShare.class);
	}
	
	/*
	public List<IncomingShare> viewIncomingByServiceDomainUserResource(Connection con, String serviceId, String domainId, String userId, String resource) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select(
				SHARES.SHARE_ID,
				SHARES.DOMAIN_ID,
				SHARES.USER_ID,
				USERS.DISPLAY_NAME.as("user_description"),
				SHARES.TARGET_USER_ID,
				SHARES.SERVICE_ID,
				SHARES.RESOURCE,
				SHARES.INSTANCE,
				SHARES.NAME,
				SHARES.PARAMETERS
			)
			.from(SHARES)
			.leftOuterJoin(USERS).on(
					SHARES.DOMAIN_ID.equal(USERS.DOMAIN_ID)
					.and(SHARES.USER_ID.equal(USERS.USER_ID))
			)
			.where(
				SHARES.SERVICE_ID.equal(serviceId)
				.and(SHARES.DOMAIN_ID.equal(domainId))
				.and(SHARES.TARGET_USER_ID.equal(userId))
				.and(SHARES.RESOURCE.equal(resource))
			)
			.fetchInto(IncomingShare.class);
	}
	
	public OShare selectById(Connection con, String id) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select(
				SHARES.SHARE_ID,
				SHARES.DOMAIN_ID,
				SHARES.USER_ID,
				SHARES.TARGET_USER_ID,
				SHARES.SERVICE_ID,
				SHARES.RESOURCE,
				SHARES.INSTANCE,
				SHARES.NAME,
				SHARES.PARAMETERS
			)
			.from(SHARES)
			.where(
				SHARES.SHARE_ID.equal(id)
			)
			.fetchOneInto(OShare.class);
	}
	
	public List<OShare> selectByDomainTargetServiceResource(Connection con, String domainId, String targetUserId, String serviceId, String resource) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select(
				SHARES.SHARE_ID,
				SHARES.DOMAIN_ID,
				SHARES.USER_ID,
				SHARES.TARGET_USER_ID,
				SHARES.SERVICE_ID,
				SHARES.RESOURCE,
				SHARES.INSTANCE,
				SHARES.NAME,
				SHARES.PARAMETERS
			)
			.from(SHARES)
			.where(
				SHARES.DOMAIN_ID.equal(domainId)
				.and(SHARES.TARGET_USER_ID.equal(targetUserId))
				.and(SHARES.SERVICE_ID.equal(serviceId))
				.and(SHARES.RESOURCE.equal(resource))
			)
			.fetchInto(OShare.class);
	}
	
	public OShare selectByDomainUserTargetServiceResourceInstance(Connection con, String domainId, String userId, String targetUserId, String serviceId, String resource, String instance) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select(
				SHARES.SHARE_ID,
				SHARES.DOMAIN_ID,
				SHARES.USER_ID,
				SHARES.TARGET_USER_ID,
				SHARES.SERVICE_ID,
				SHARES.RESOURCE,
				SHARES.INSTANCE,
				SHARES.NAME,
				SHARES.PARAMETERS
			)
			.from(SHARES)
			.where(
				SHARES.DOMAIN_ID.equal(domainId)
				.and(SHARES.USER_ID.equal(userId))
				.and(SHARES.TARGET_USER_ID.equal(targetUserId))
				.and(SHARES.SERVICE_ID.equal(serviceId))
				.and(SHARES.RESOURCE.equal(resource))
				.and(SHARES.INSTANCE.equal(instance))
			)
			.fetchOneInto(OShare.class);
	}
	*/
	
	public int insert(Connection con, OShare item) throws DAOException {
		DSLContext dsl = getDSL(con);
		SharesRecord record = dsl.newRecord(SHARES, item);
		return dsl
			.insertInto(SHARES)
			.set(record)
			.execute();
	}
	
	public int deleteById(Connection con, int shareId) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.delete(SHARES)
			.where(
					SHARES.SHARE_ID.equal(shareId)
			)
			.execute();
	}
	
	public int deleteByUserServiceKeyInstance(Connection con, String userUid, String serviceId, String shareKey, String instance) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.delete(SHARES)
			.where(
					SHARES.USER_UID.equal(userUid)
					.and(SHARES.SERVICE_ID.equal(serviceId))
					.and(SHARES.KEY.equal(shareKey))
					.and(SHARES.INSTANCE.equal(instance))
			)
			.execute();
	}
	
	public int deleteByDomain(Connection con, String domainId) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.delete(SHARES_DATA)
			.where(
					SHARES_DATA.USER_UID.in(
							DSL.select(
								USERS.USER_UID
							)
							.from(USERS)
							.where(
									USERS.DOMAIN_ID.equal(domainId)
							)
					)
			)
			.execute();
	}
}
