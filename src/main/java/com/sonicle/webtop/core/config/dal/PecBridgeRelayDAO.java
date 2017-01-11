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

import com.sonicle.webtop.core.config.bol.OPecBridgeRelay;
import com.sonicle.webtop.core.dal.BaseDAO;
import com.sonicle.webtop.core.dal.DAOException;
import static com.sonicle.webtop.core.jooq.config.Sequences.SEQ_PECBRIDGE_RELAYS;
import static com.sonicle.webtop.core.jooq.config.Tables.PECBRIDGE_RELAYS;
import com.sonicle.webtop.core.jooq.config.tables.records.PecbridgeRelaysRecord;
import java.sql.Connection;
import java.util.List;
import org.jooq.DSLContext;

/**
 *
 * @author malbinola
 */
public class PecBridgeRelayDAO extends BaseDAO {
	private final static PecBridgeRelayDAO INSTANCE = new PecBridgeRelayDAO();

	public static PecBridgeRelayDAO getInstance() {
		return INSTANCE;
	}

	public Long getSequence(Connection con) throws DAOException {
		DSLContext dsl = getDSL(con);
		Long nextID = dsl.nextval(SEQ_PECBRIDGE_RELAYS);
		return nextID;
	}
	
	public List<OPecBridgeRelay> selectAll(Connection con) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select(
					PECBRIDGE_RELAYS.RELAY_ID,
					PECBRIDGE_RELAYS.CONTEXT,
					PECBRIDGE_RELAYS.MATCHER,
					PECBRIDGE_RELAYS.HOST,
					PECBRIDGE_RELAYS.PORT,
					PECBRIDGE_RELAYS.PROTOCOL,
					PECBRIDGE_RELAYS.USERNAME,
					PECBRIDGE_RELAYS.PASSWORD,
					PECBRIDGE_RELAYS.DEBUG,
					PECBRIDGE_RELAYS.WEBTOP_PROFILE_ID
			).from(PECBRIDGE_RELAYS)
			.orderBy(
					PECBRIDGE_RELAYS.MATCHER.asc()
			)
			.fetchInto(OPecBridgeRelay.class);
	}
	
	public List<OPecBridgeRelay> selectByContext(Connection con, String context) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select(
					PECBRIDGE_RELAYS.RELAY_ID,
					PECBRIDGE_RELAYS.CONTEXT,
					PECBRIDGE_RELAYS.MATCHER,
					PECBRIDGE_RELAYS.HOST,
					PECBRIDGE_RELAYS.PORT,
					PECBRIDGE_RELAYS.PROTOCOL,
					PECBRIDGE_RELAYS.USERNAME,
					PECBRIDGE_RELAYS.PASSWORD,
					PECBRIDGE_RELAYS.DEBUG,
					PECBRIDGE_RELAYS.WEBTOP_PROFILE_ID
			).from(PECBRIDGE_RELAYS)
			.where(
					PECBRIDGE_RELAYS.CONTEXT.equal(context)
			)
			.orderBy(
					PECBRIDGE_RELAYS.MATCHER.asc()
			)
			.fetchInto(OPecBridgeRelay.class);
	}
	
	public OPecBridgeRelay select(Connection con, int relayId) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select(
					PECBRIDGE_RELAYS.RELAY_ID,
					PECBRIDGE_RELAYS.CONTEXT,
					PECBRIDGE_RELAYS.MATCHER,
					PECBRIDGE_RELAYS.HOST,
					PECBRIDGE_RELAYS.PORT,
					PECBRIDGE_RELAYS.PROTOCOL,
					PECBRIDGE_RELAYS.USERNAME,
					PECBRIDGE_RELAYS.PASSWORD,
					PECBRIDGE_RELAYS.DEBUG,
					PECBRIDGE_RELAYS.WEBTOP_PROFILE_ID
			).from(PECBRIDGE_RELAYS)
			.where(
					PECBRIDGE_RELAYS.RELAY_ID.equal(relayId)
			)
			.fetchOneInto(OPecBridgeRelay.class);
	}
	
	public int insert(Connection con, OPecBridgeRelay item) throws DAOException {
		DSLContext dsl = getDSL(con);
		PecbridgeRelaysRecord record = dsl.newRecord(PECBRIDGE_RELAYS, item);
		return dsl
			.insertInto(PECBRIDGE_RELAYS)
			.set(record)
			.execute();
	}
	
	public int update(Connection con, OPecBridgeRelay item) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.update(PECBRIDGE_RELAYS)
			.set(PECBRIDGE_RELAYS.MATCHER, item.getMatcher())
			.set(PECBRIDGE_RELAYS.HOST, item.getHost())
			.set(PECBRIDGE_RELAYS.PORT, item.getPort())
			.set(PECBRIDGE_RELAYS.PROTOCOL, item.getProtocol())
			.set(PECBRIDGE_RELAYS.USERNAME, item.getUsername())
			.set(PECBRIDGE_RELAYS.PASSWORD, item.getPassword())
			.set(PECBRIDGE_RELAYS.WEBTOP_PROFILE_ID, item.getWebtopProfileId())
			.where(
					PECBRIDGE_RELAYS.RELAY_ID.equal(item.getRelayId())
			)
			.execute();
	}

	public int deleteByIdContext(Connection con, int relayId, String context) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.delete(PECBRIDGE_RELAYS)
			.where(
					PECBRIDGE_RELAYS.RELAY_ID.equal(relayId)
					.and(PECBRIDGE_RELAYS.CONTEXT.equal(context))
			)
			.execute();
	}
	
	public int deleteByContext(Connection con, String context) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.delete(PECBRIDGE_RELAYS)
			.where(
					PECBRIDGE_RELAYS.CONTEXT.equal(context)
			)
			.execute();
	}
}
