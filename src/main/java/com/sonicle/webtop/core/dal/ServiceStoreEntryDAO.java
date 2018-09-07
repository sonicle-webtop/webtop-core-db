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

import com.sonicle.webtop.core.bol.OServiceStoreEntry;
import static com.sonicle.webtop.core.jooq.core.Tables.*;
import java.sql.Connection;
import java.util.List;
import org.jooq.DSLContext;

/**
 *
 * @author malbinola
 */
public class ServiceStoreEntryDAO extends BaseDAO {
	private final static ServiceStoreEntryDAO INSTANCE = new ServiceStoreEntryDAO();
	public static ServiceStoreEntryDAO getInstance() {
		return INSTANCE;
	}
	
	public OServiceStoreEntry select(Connection con, String domainId, String userId, String serviceId, String context, String key) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select(
				SERVICESTORE_ENTRIES.DOMAIN_ID,
				SERVICESTORE_ENTRIES.USER_ID,
				SERVICESTORE_ENTRIES.SERVICE_ID,
				SERVICESTORE_ENTRIES.CONTEXT,
				SERVICESTORE_ENTRIES.KEY,
				SERVICESTORE_ENTRIES.VALUE,
				SERVICESTORE_ENTRIES.FREQUENCY,
				SERVICESTORE_ENTRIES.LAST_UPDATE
			)
			.from(SERVICESTORE_ENTRIES)
			.where(
				SERVICESTORE_ENTRIES.DOMAIN_ID.equal(domainId)
				.and(SERVICESTORE_ENTRIES.USER_ID.equal(userId))
				.and(SERVICESTORE_ENTRIES.SERVICE_ID.equal(serviceId))
				.and(SERVICESTORE_ENTRIES.CONTEXT.equal(context))
				.and(SERVICESTORE_ENTRIES.KEY.equal(OServiceStoreEntry.sanitizeKey(key))) // Targets always the trimmed key: " ABC" or "ABC" are the same!
			)
			.fetchOneInto(OServiceStoreEntry.class);
	}
	
	public List<OServiceStoreEntry> selectKeyValueByLimit(Connection con, String domainId, String userId, String serviceId, String context, int limit) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select(
				SERVICESTORE_ENTRIES.KEY,
				SERVICESTORE_ENTRIES.VALUE
			)
			.from(SERVICESTORE_ENTRIES)
			.where(
				SERVICESTORE_ENTRIES.DOMAIN_ID.equal(domainId)
				.and(SERVICESTORE_ENTRIES.USER_ID.equal(userId))
				.and(SERVICESTORE_ENTRIES.SERVICE_ID.equal(serviceId))
				.and(SERVICESTORE_ENTRIES.CONTEXT.equal(context))
			)
			.orderBy(
				SERVICESTORE_ENTRIES.FREQUENCY.desc(),
				SERVICESTORE_ENTRIES.VALUE.asc()
			)
			.limit(limit)
			.fetchInto(OServiceStoreEntry.class);
	}
	
	public List<OServiceStoreEntry> selectKeyValueByLikeKeyLimit(Connection con, String domainId, String userId, String serviceId, String context, String likeKey, int limit) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select(
				SERVICESTORE_ENTRIES.KEY,
				SERVICESTORE_ENTRIES.VALUE
			)
			.from(SERVICESTORE_ENTRIES)
			.where(
				SERVICESTORE_ENTRIES.DOMAIN_ID.equal(domainId)
				.and(SERVICESTORE_ENTRIES.USER_ID.equal(userId))
				.and(SERVICESTORE_ENTRIES.SERVICE_ID.equal(serviceId))
				.and(SERVICESTORE_ENTRIES.CONTEXT.equal(context))
				.and(SERVICESTORE_ENTRIES.KEY.like(likeKey))
			)
			.orderBy(
				SERVICESTORE_ENTRIES.FREQUENCY.desc(),
				SERVICESTORE_ENTRIES.VALUE.asc()
			)
			.limit(limit)
			.fetchInto(OServiceStoreEntry.class);
	}
	
	public int insert(Connection con, OServiceStoreEntry item) throws DAOException {
		DSLContext dsl = getDSL(con);
		item.setLastUpdate(createRevisionTimestamp());
		//ServicestoreEntriesRecord record = dsl.newRecord(SERVICESTORE_ENTRIES, item);
		return dsl
			.insertInto(SERVICESTORE_ENTRIES)
			.set(SERVICESTORE_ENTRIES.DOMAIN_ID, item.getDomainId())
			.set(SERVICESTORE_ENTRIES.USER_ID, item.getUserId())
			.set(SERVICESTORE_ENTRIES.SERVICE_ID, item.getServiceId())
			.set(SERVICESTORE_ENTRIES.CONTEXT, item.getContext())
			.set(SERVICESTORE_ENTRIES.KEY, item.getSanitizedKey())
			.set(SERVICESTORE_ENTRIES.VALUE, item.getValue())
			.set(SERVICESTORE_ENTRIES.FREQUENCY, item.getFrequency())
			.set(SERVICESTORE_ENTRIES.LAST_UPDATE, item.getLastUpdate())
			.execute();
	}
	
	public int update(Connection con, String domainId, String userId, String serviceId, String context, String key) throws DAOException {
		return update(con, domainId, userId, serviceId, context, key, null);
	}
	
	public int update(Connection con, String domainId, String userId, String serviceId, String context, String key, String value) throws DAOException {
		DSLContext dsl = getDSL(con);
		
		if (value != null) {
			return dsl
				.update(SERVICESTORE_ENTRIES)
				.set(SERVICESTORE_ENTRIES.VALUE, value)
				.set(SERVICESTORE_ENTRIES.FREQUENCY, SERVICESTORE_ENTRIES.FREQUENCY.add(1))
				.set(SERVICESTORE_ENTRIES.LAST_UPDATE, createRevisionTimestamp())
				.where(
					SERVICESTORE_ENTRIES.DOMAIN_ID.equal(domainId)
					.and(SERVICESTORE_ENTRIES.USER_ID.equal(userId))
					.and(SERVICESTORE_ENTRIES.SERVICE_ID.equal(serviceId))
					.and(SERVICESTORE_ENTRIES.CONTEXT.equal(context))
					.and(SERVICESTORE_ENTRIES.KEY.equal(OServiceStoreEntry.sanitizeKey(key)))
				)
				.execute();
		} else {
			return dsl
				.update(SERVICESTORE_ENTRIES)
				.set(SERVICESTORE_ENTRIES.FREQUENCY, SERVICESTORE_ENTRIES.FREQUENCY.add(1))
				.set(SERVICESTORE_ENTRIES.LAST_UPDATE, createRevisionTimestamp())
				.where(
					SERVICESTORE_ENTRIES.DOMAIN_ID.equal(domainId)
					.and(SERVICESTORE_ENTRIES.USER_ID.equal(userId))
					.and(SERVICESTORE_ENTRIES.SERVICE_ID.equal(serviceId))
					.and(SERVICESTORE_ENTRIES.CONTEXT.equal(context))
					.and(SERVICESTORE_ENTRIES.KEY.equal(OServiceStoreEntry.sanitizeKey(key)))
				)
				.execute();
		}
	}
	
	/*
	public int update(Connection con, OServiceStoreEntry item) throws DAOException {
		DSLContext dsl = getDSL(con);
		item.setLastUpdate(createRevisionTimestamp());
		return dsl
			.update(SERVICESTORE_ENTRIES)
			.set(SERVICESTORE_ENTRIES.VALUE, item.getValue())
			.set(SERVICESTORE_ENTRIES.FREQUENCY, item.getFrequency())
			.set(SERVICESTORE_ENTRIES.LAST_UPDATE, item.getLastUpdate())
			.where(
				SERVICESTORE_ENTRIES.DOMAIN_ID.equal(item.getDomainId())
				.and(SERVICESTORE_ENTRIES.USER_ID.equal(item.getUserId()))
				.and(SERVICESTORE_ENTRIES.SERVICE_ID.equal(item.getServiceId()))
				.and(SERVICESTORE_ENTRIES.CONTEXT.equal(item.getContext()))
				.and(SERVICESTORE_ENTRIES.KEY.equal(item.getSanitizedKey()))
			)
			.execute();
	}
	*/
	
	public int delete(Connection con, String domainId, String userId, String serviceId, String context, String key) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.delete(SERVICESTORE_ENTRIES)
			.where(
				SERVICESTORE_ENTRIES.DOMAIN_ID.equal(domainId)
				.and(SERVICESTORE_ENTRIES.USER_ID.equal(userId))
				.and(SERVICESTORE_ENTRIES.SERVICE_ID.equal(serviceId))
				.and(SERVICESTORE_ENTRIES.CONTEXT.equal(context))
				.and(SERVICESTORE_ENTRIES.KEY.trim().equal(OServiceStoreEntry.sanitizeKey(key))) // Targets trimmed (and not) keys: " ABC" or "ABC" are the same! Note timmming on field!
			)
			.execute();
	}
	
	public int deleteByDomainUserService(Connection con, String domainId, String userId, String serviceId) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.delete(SERVICESTORE_ENTRIES)
			.where(
				SERVICESTORE_ENTRIES.DOMAIN_ID.equal(domainId)
				.and(SERVICESTORE_ENTRIES.USER_ID.equal(userId))
				.and(SERVICESTORE_ENTRIES.SERVICE_ID.equal(serviceId))
			)
			.execute();
	}
	
	public int deleteByDomainUser(Connection con, String domainId, String userId) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.delete(SERVICESTORE_ENTRIES)
			.where(
				SERVICESTORE_ENTRIES.DOMAIN_ID.equal(domainId)
				.and(SERVICESTORE_ENTRIES.USER_ID.equal(userId))
			)
			.execute();
	}
	
	public int deleteByDomain(Connection con, String domainId) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.delete(SERVICESTORE_ENTRIES)
			.where(
				SERVICESTORE_ENTRIES.DOMAIN_ID.equal(domainId)
			)
			.execute();
	}
}
