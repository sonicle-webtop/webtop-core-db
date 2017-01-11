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

import com.sonicle.webtop.core.bol.OShareData;
import static com.sonicle.webtop.core.jooq.core.Tables.*;
import com.sonicle.webtop.core.jooq.core.tables.records.SharesDataRecord;
import java.sql.Connection;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

/**
 *
 * @author malbinola
 */
public class ShareDataDAO extends BaseDAO {
	private final static ShareDataDAO INSTANCE = new ShareDataDAO();
	public static ShareDataDAO getInstance() {
		return INSTANCE;
	}
	
	public OShareData selectByShareUser(Connection con, int shareId, String userUid) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select(
				SHARES_DATA.fields()
			)
			.from(SHARES_DATA)
			.where(
					SHARES_DATA.SHARE_ID.equal(shareId)
					.and(SHARES_DATA.USER_UID.equal(userUid))
			)
			.fetchOneInto(OShareData.class);
	}
	
	public int insert(Connection con, OShareData item) throws DAOException {
		DSLContext dsl = getDSL(con);
		SharesDataRecord record = dsl.newRecord(SHARES_DATA, item);
		return dsl
			.insertInto(SHARES_DATA)
			.set(record)
			.execute();
	}
	
	public int update(Connection con, OShareData item) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.update(SHARES_DATA)
			.set(SHARES_DATA.VALUE, item.getValue())
			.where(
					SHARES_DATA.SHARE_ID.equal(item.getShareId())
					.and(SHARES_DATA.USER_UID.equal(item.getUserUid()))
			)
			.execute();
	}
	
	public int deleteByShareUser(Connection con, int shareId, String userUid) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.delete(SHARES_DATA)
			.where(
					SHARES_DATA.SHARE_ID.equal(shareId)
					.and(SHARES_DATA.USER_UID.equal(userUid))
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
