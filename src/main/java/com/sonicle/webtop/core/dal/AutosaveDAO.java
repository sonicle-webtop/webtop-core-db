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

import com.sonicle.webtop.core.bol.OAutosave;
import static com.sonicle.webtop.core.jooq.core.Tables.*;
import com.sonicle.webtop.core.jooq.core.tables.records.AutosaveRecord;
import java.sql.Connection;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.jooq.DSLContext;

/**
 *
 * @author malbinola
 */
public class AutosaveDAO extends BaseDAO {
	
	private final static AutosaveDAO INSTANCE = new AutosaveDAO();
	public static AutosaveDAO getInstance() {
		return INSTANCE;
	}
	
	public OAutosave select(Connection con, String domainId, String userId, String webtopClientId, String serviceId, String context, String key) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select(
				AUTOSAVE.DOMAIN_ID,
				AUTOSAVE.USER_ID,
				AUTOSAVE.WEBTOP_CLIENT_ID,
				AUTOSAVE.SERVICE_ID,
				AUTOSAVE.CONTEXT,
				AUTOSAVE.KEY,
				AUTOSAVE.VALUE
			).from(AUTOSAVE)
			.where(
				AUTOSAVE.DOMAIN_ID.equal(domainId)
				.and(AUTOSAVE.USER_ID.equal(userId))
				.and(AUTOSAVE.WEBTOP_CLIENT_ID.equal(webtopClientId))
				.and(AUTOSAVE.SERVICE_ID.equal(serviceId))
				.and(AUTOSAVE.CONTEXT.equal(context))
				.and(AUTOSAVE.KEY.equal(StringUtils.upperCase(key)))
			)
			.fetchOneInto(OAutosave.class);
	}
	
	public List<OAutosave> selectMineByUserServices(Connection con, String domainId, String userId, String webtopClientId, List<String> serviceIds) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select(
				AUTOSAVE.DOMAIN_ID,
				AUTOSAVE.USER_ID,
				AUTOSAVE.WEBTOP_CLIENT_ID,
				AUTOSAVE.SERVICE_ID,
				AUTOSAVE.CONTEXT,
				AUTOSAVE.KEY,
				AUTOSAVE.VALUE
			).from(AUTOSAVE)
			.where(
				AUTOSAVE.DOMAIN_ID.equal(domainId)
				.and(AUTOSAVE.USER_ID.equal(userId))
				.and(AUTOSAVE.WEBTOP_CLIENT_ID.equal(webtopClientId))
				.and(AUTOSAVE.SERVICE_ID.in(serviceIds))
			)
			.fetchInto(OAutosave.class);
	}
	
	public List<OAutosave> selectOthersByUserServices(Connection con, String domainId, String userId, String notWebtopClientId, List<String> serviceIds) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select(
				AUTOSAVE.DOMAIN_ID,
				AUTOSAVE.USER_ID,
				AUTOSAVE.WEBTOP_CLIENT_ID,
				AUTOSAVE.SERVICE_ID,
				AUTOSAVE.CONTEXT,
				AUTOSAVE.KEY,
				AUTOSAVE.VALUE
			).from(AUTOSAVE)
			.where(
				AUTOSAVE.DOMAIN_ID.equal(domainId)
				.and(AUTOSAVE.USER_ID.equal(userId))
				.and(AUTOSAVE.WEBTOP_CLIENT_ID.notEqual(notWebtopClientId))
				.and(AUTOSAVE.SERVICE_ID.in(serviceIds))
			)
			.fetchInto(OAutosave.class);
	}
	
	public int countMineByUserServices(Connection con, String domainId, String userId, String webtopClientId, List<String> serviceIds) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.selectCount()
			.from(AUTOSAVE)
			.where(
				AUTOSAVE.DOMAIN_ID.equal(domainId)
				.and(AUTOSAVE.USER_ID.equal(userId))
				.and(AUTOSAVE.WEBTOP_CLIENT_ID.equal(webtopClientId))
				.and(AUTOSAVE.SERVICE_ID.in(serviceIds))
			)
			.fetchOne(0, int.class);
	}
	
	public int countOthersByUserServices(Connection con, String domainId, String userId, String notWebtopClientId, List<String> serviceIds) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.selectCount()
			.from(AUTOSAVE)
			.where(
				AUTOSAVE.DOMAIN_ID.equal(domainId)
				.and(AUTOSAVE.USER_ID.equal(userId))
				.and(AUTOSAVE.WEBTOP_CLIENT_ID.notEqual(notWebtopClientId))
				.and(AUTOSAVE.SERVICE_ID.in(serviceIds))
			)
			.fetchOne(0, int.class);
		}
	public int insert(Connection con, OAutosave item) throws DAOException {
		DSLContext dsl = getDSL(con);
		AutosaveRecord record = dsl.newRecord(AUTOSAVE, item);
		return dsl
			.insertInto(AUTOSAVE)
			.set(record)
			.execute();
	}
	
	public int update(Connection con, OAutosave item) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.update(AUTOSAVE)
			.set(AUTOSAVE.VALUE, item.getValue())
			.where(
				AUTOSAVE.DOMAIN_ID.equal(item.getDomainId())
				.and(AUTOSAVE.USER_ID.equal(item.getUserId()))
				.and(AUTOSAVE.WEBTOP_CLIENT_ID.equal(item.getWebtopClientId()))
				.and(AUTOSAVE.SERVICE_ID.equal(item.getServiceId()))
				.and(AUTOSAVE.CONTEXT.equal(item.getContext()))
				.and(AUTOSAVE.KEY.equal(StringUtils.upperCase(item.getKey())))
			)
			.execute();
	}
	
	public int deleteByDomain(Connection con, String domainId) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.delete(AUTOSAVE)
			.where(
				AUTOSAVE.DOMAIN_ID.equal(domainId)
			)
			.execute();
	}
	
	public int deleteByUser(Connection con, String domainId, String userId) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.delete(AUTOSAVE)
			.where(
				AUTOSAVE.DOMAIN_ID.equal(domainId)
				.and(AUTOSAVE.USER_ID.equal(userId))
			)
			.execute();
	}
	
	public int deleteByWebtopClientId(Connection con, String domainId, String userId, String webtopClientId) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.delete(AUTOSAVE)
			.where(
				AUTOSAVE.DOMAIN_ID.equal(domainId)
				.and(AUTOSAVE.USER_ID.equal(userId))
				.and(AUTOSAVE.WEBTOP_CLIENT_ID.equal(webtopClientId))
			)
			.execute();
	}
	
	public int deleteByNotWebtopClientId(Connection con, String domainId, String userId, String notWebtopClientId) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.delete(AUTOSAVE)
			.where(
				AUTOSAVE.DOMAIN_ID.equal(domainId)
				.and(AUTOSAVE.USER_ID.equal(userId))
				.and(AUTOSAVE.WEBTOP_CLIENT_ID.notEqual(notWebtopClientId))
			)
			.execute();
	}
	
	public int deleteByService(Connection con, String domainId, String userId, String webtopClientId, String serviceId) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.delete(AUTOSAVE)
			.where(
				AUTOSAVE.DOMAIN_ID.equal(domainId)
				.and(AUTOSAVE.USER_ID.equal(userId))
				.and(AUTOSAVE.WEBTOP_CLIENT_ID.equal(webtopClientId))
				.and(AUTOSAVE.SERVICE_ID.equal(serviceId))
			)
			.execute();
	}
	
	public int deleteByContext(Connection con, String domainId, String userId, String webtopClientId, String serviceId, String context) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.delete(AUTOSAVE)
			.where(
				AUTOSAVE.DOMAIN_ID.equal(domainId)
				.and(AUTOSAVE.USER_ID.equal(userId))
				.and(AUTOSAVE.WEBTOP_CLIENT_ID.equal(webtopClientId))
				.and(AUTOSAVE.SERVICE_ID.equal(serviceId))
				.and(AUTOSAVE.CONTEXT.equal(context))
			)
			.execute();
	}
	
	public int deleteByKey(Connection con, String domainId, String userId, String webtopClientId, String serviceId, String context, String key) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.delete(AUTOSAVE)
			.where(
				AUTOSAVE.DOMAIN_ID.equal(domainId)
				.and(AUTOSAVE.USER_ID.equal(userId))
				.and(AUTOSAVE.WEBTOP_CLIENT_ID.equal(webtopClientId))
				.and(AUTOSAVE.SERVICE_ID.equal(serviceId))
				.and(AUTOSAVE.CONTEXT.equal(context))
				.and(AUTOSAVE.KEY.equal(StringUtils.upperCase(key)))
			)
			.execute();
	}
}
