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
package com.sonicle.webtop.core.config.dal;

import com.sonicle.webtop.core.config.bol.OPecBridgeFetcher;
import com.sonicle.webtop.core.dal.BaseDAO;
import com.sonicle.webtop.core.dal.DAOException;
import static com.sonicle.webtop.core.jooq.config.Sequences.SEQ_PECBRIDGE_FETCHERS;
import static com.sonicle.webtop.core.jooq.config.Tables.PECBRIDGE_FETCHERS;
import com.sonicle.webtop.core.jooq.config.tables.records.PecbridgeFetchersRecord;
import java.sql.Connection;
import java.util.List;
import org.jooq.DSLContext;

/**
 *
 * @author malbinola
 */
public class PecBridgeFetcherDAO extends BaseDAO {
	private final static PecBridgeFetcherDAO INSTANCE = new PecBridgeFetcherDAO();

	public static PecBridgeFetcherDAO getInstance() {
		return INSTANCE;
	}

	public Long getSequence(Connection con) throws DAOException {
		DSLContext dsl = getDSL(con);
		Long nextID = dsl.nextval(SEQ_PECBRIDGE_FETCHERS);
		return nextID;
	}
	
	public List<OPecBridgeFetcher> selectAll(Connection con) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select(
					PECBRIDGE_FETCHERS.FETCHER_ID,
					PECBRIDGE_FETCHERS.CONTEXT,
					PECBRIDGE_FETCHERS.FORWARD_ADDRESS,
					PECBRIDGE_FETCHERS.DELETE_ON_FORWARD,
					PECBRIDGE_FETCHERS.HOST,
					PECBRIDGE_FETCHERS.PORT,
					PECBRIDGE_FETCHERS.PROTOCOL,
					PECBRIDGE_FETCHERS.USERNAME,
					PECBRIDGE_FETCHERS.PASSWORD,
					PECBRIDGE_FETCHERS.WEBTOP_PROFILE_ID
			).from(PECBRIDGE_FETCHERS)
			.orderBy(
					PECBRIDGE_FETCHERS.FORWARD_ADDRESS.asc()
			)
			.fetchInto(OPecBridgeFetcher.class);
	}
	
	public List<OPecBridgeFetcher> selectByContext(Connection con, String context) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select(
					PECBRIDGE_FETCHERS.FETCHER_ID,
					PECBRIDGE_FETCHERS.CONTEXT,
					PECBRIDGE_FETCHERS.FORWARD_ADDRESS,
					PECBRIDGE_FETCHERS.DELETE_ON_FORWARD,
					PECBRIDGE_FETCHERS.HOST,
					PECBRIDGE_FETCHERS.PORT,
					PECBRIDGE_FETCHERS.PROTOCOL,
					PECBRIDGE_FETCHERS.USERNAME,
					PECBRIDGE_FETCHERS.PASSWORD,
					PECBRIDGE_FETCHERS.WEBTOP_PROFILE_ID
			).from(PECBRIDGE_FETCHERS)
			.where(
					PECBRIDGE_FETCHERS.CONTEXT.equal(context)
			)
			.orderBy(
					PECBRIDGE_FETCHERS.FORWARD_ADDRESS.asc()
			)
			.fetchInto(OPecBridgeFetcher.class);
	}
	
	public OPecBridgeFetcher select(Connection con, int fetcherId) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select(
					PECBRIDGE_FETCHERS.FETCHER_ID,
					PECBRIDGE_FETCHERS.CONTEXT,
					PECBRIDGE_FETCHERS.FORWARD_ADDRESS,
					PECBRIDGE_FETCHERS.DELETE_ON_FORWARD,
					PECBRIDGE_FETCHERS.HOST,
					PECBRIDGE_FETCHERS.PORT,
					PECBRIDGE_FETCHERS.PROTOCOL,
					PECBRIDGE_FETCHERS.USERNAME,
					PECBRIDGE_FETCHERS.PASSWORD,
					PECBRIDGE_FETCHERS.WEBTOP_PROFILE_ID
			).from(PECBRIDGE_FETCHERS)
			.where(
					PECBRIDGE_FETCHERS.FETCHER_ID.equal(fetcherId)
			)
			.fetchOneInto(OPecBridgeFetcher.class);
	}
	
	public int insert(Connection con, OPecBridgeFetcher item) throws DAOException {
		DSLContext dsl = getDSL(con);
		PecbridgeFetchersRecord record = dsl.newRecord(PECBRIDGE_FETCHERS, item);
		return dsl
			.insertInto(PECBRIDGE_FETCHERS)
			.set(record)
			.execute();
	}
	
	public int update(Connection con, OPecBridgeFetcher item) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.update(PECBRIDGE_FETCHERS)
			.set(PECBRIDGE_FETCHERS.FORWARD_ADDRESS, item.getForwardAddress())
			.set(PECBRIDGE_FETCHERS.DELETE_ON_FORWARD, item.getDeleteOnForward())
			.set(PECBRIDGE_FETCHERS.HOST, item.getHost())
			.set(PECBRIDGE_FETCHERS.PORT, item.getPort())
			.set(PECBRIDGE_FETCHERS.PROTOCOL, item.getProtocol())
			.set(PECBRIDGE_FETCHERS.USERNAME, item.getUsername())
			.set(PECBRIDGE_FETCHERS.PASSWORD, item.getPassword())
			.set(PECBRIDGE_FETCHERS.WEBTOP_PROFILE_ID, item.getWebtopProfileId())
			.where(
					PECBRIDGE_FETCHERS.FETCHER_ID.equal(item.getFetcherId())
			)
			.execute();
	}
	
	public int deleteByIdContext(Connection con, int fetcherId, String context) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.delete(PECBRIDGE_FETCHERS)
			.where(
					PECBRIDGE_FETCHERS.FETCHER_ID.equal(fetcherId)
					.and(PECBRIDGE_FETCHERS.CONTEXT.equal(context))
			)
			.execute();
	}
	
	public int deleteByContext(Connection con, String context) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.delete(PECBRIDGE_FETCHERS)
			.where(
					PECBRIDGE_FETCHERS.CONTEXT.equal(context)
			)
			.execute();
	}
}
