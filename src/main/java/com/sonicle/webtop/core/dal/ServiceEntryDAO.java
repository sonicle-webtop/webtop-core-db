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

import com.sonicle.webtop.core.bol.OServiceEntry;
import static com.sonicle.webtop.core.jooq.Tables.SERVICE_ENTRIES;
import com.sonicle.webtop.core.jooq.tables.records.ServiceEntriesRecord;
import java.sql.Connection;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.jooq.DSLContext;

/**
 *
 * @author malbinola
 */
public class ServiceEntryDAO extends BaseDAO {
	
	private final static ServiceEntryDAO INSTANCE = new ServiceEntryDAO();
	public static ServiceEntryDAO getInstance() {
		return INSTANCE;
	}
	
	public OServiceEntry select(Connection con, String domainId, String userId, String serviceId, String context, String key) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select(
				SERVICE_ENTRIES.DOMAIN_ID,
				SERVICE_ENTRIES.USER_ID,
				SERVICE_ENTRIES.SERVICE_ID,
				SERVICE_ENTRIES.CONTEXT,
				SERVICE_ENTRIES.KEY,
				SERVICE_ENTRIES.VALUE,
				SERVICE_ENTRIES.FREQUENCY,
				SERVICE_ENTRIES.LAST_UPDATE
			).from(SERVICE_ENTRIES)
			.where(
				SERVICE_ENTRIES.DOMAIN_ID.equal(domainId)
				.and(SERVICE_ENTRIES.USER_ID.equal(userId))
				.and(SERVICE_ENTRIES.SERVICE_ID.equal(serviceId))
				.and(SERVICE_ENTRIES.CONTEXT.equal(context))
				.and(SERVICE_ENTRIES.KEY.equal(StringUtils.upperCase(key)))
			)
			.fetchOneInto(OServiceEntry.class);
	}
	
	public List<OServiceEntry> selectKeyValueByLikeKey(Connection con, String domainId, String userId, String serviceId, String context, String likeKey) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select(
				SERVICE_ENTRIES.KEY,
				SERVICE_ENTRIES.VALUE
			).from(SERVICE_ENTRIES)
			.where(
				SERVICE_ENTRIES.DOMAIN_ID.equal(domainId)
				.and(SERVICE_ENTRIES.USER_ID.equal(userId))
				.and(SERVICE_ENTRIES.SERVICE_ID.equal(serviceId))
				.and(SERVICE_ENTRIES.CONTEXT.equal(context))
				.and(SERVICE_ENTRIES.KEY.like(StringUtils.upperCase(likeKey)))
			)
			.orderBy(
				SERVICE_ENTRIES.FREQUENCY.desc(),
				SERVICE_ENTRIES.VALUE.asc()
			)
			.fetchInto(OServiceEntry.class);
	}
	
	public int insert(Connection con, OServiceEntry item) throws DAOException {
		DSLContext dsl = getDSL(con);
		ServiceEntriesRecord record = dsl.newRecord(SERVICE_ENTRIES, item);
		return dsl
			.insertInto(SERVICE_ENTRIES)
			.set(record)
			.execute();
	}
	
	public int update(Connection con, OServiceEntry item) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.update(SERVICE_ENTRIES)
			.set(SERVICE_ENTRIES.VALUE, item.getValue())
			.set(SERVICE_ENTRIES.FREQUENCY, item.getFrequency())
			.set(SERVICE_ENTRIES.LAST_UPDATE, item.getLastUpdate())
			.where(
				SERVICE_ENTRIES.DOMAIN_ID.equal(item.getDomainId())
				.and(SERVICE_ENTRIES.USER_ID.equal(item.getUserId()))
				.and(SERVICE_ENTRIES.SERVICE_ID.equal(item.getServiceId()))
				.and(SERVICE_ENTRIES.CONTEXT.equal(item.getContext()))
				.and(SERVICE_ENTRIES.KEY.equal(StringUtils.upperCase(item.getKey())))
			)
			.execute();
	}
	
	public int delete(Connection con, String domainId, String userId, String serviceId, String context, String key) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.delete(SERVICE_ENTRIES)
			.where(
				SERVICE_ENTRIES.DOMAIN_ID.equal(domainId)
				.and(SERVICE_ENTRIES.USER_ID.equal(userId))
				.and(SERVICE_ENTRIES.SERVICE_ID.equal(serviceId))
				.and(SERVICE_ENTRIES.CONTEXT.equal(context))
				.and(SERVICE_ENTRIES.KEY.equal(StringUtils.upperCase(key)))
			)
			.execute();
	}
	
	public int deleteByDomainUserService(Connection con, String domainId, String userId, String serviceId) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.delete(SERVICE_ENTRIES)
			.where(
				SERVICE_ENTRIES.DOMAIN_ID.equal(domainId)
				.and(SERVICE_ENTRIES.USER_ID.equal(userId))
				.and(SERVICE_ENTRIES.SERVICE_ID.equal(serviceId))
			)
			.execute();
	}
	
	public int deleteByDomainUser(Connection con, String domainId, String userId) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.delete(SERVICE_ENTRIES)
			.where(
				SERVICE_ENTRIES.DOMAIN_ID.equal(domainId)
				.and(SERVICE_ENTRIES.USER_ID.equal(userId))
			)
			.execute();
	}
}
