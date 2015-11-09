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

import com.sonicle.webtop.core.bol.OShareParameter;
import static com.sonicle.webtop.core.jooq.Tables.SHARE_PARAMETERS;
import com.sonicle.webtop.core.jooq.tables.records.ShareParametersRecord;
import java.sql.Connection;
import java.util.List;
import org.jooq.DSLContext;

/**
 *
 * @author malbinola
 */
public class ShareParameterDAO extends BaseDAO {
	private final static ShareDAO INSTANCE = new ShareDAO();
	public static ShareDAO getInstance() {
		return INSTANCE;
	}
	
	public List<OShareParameter> selectByShareUser(Connection con, String shareId, String userUid) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select(
				SHARE_PARAMETERS.fields()
			)
			.from(SHARE_PARAMETERS)
			.where(
					SHARE_PARAMETERS.SHARE_ID.equal(shareId)
					.and(SHARE_PARAMETERS.USER_UID.equal(userUid))
			)
			.fetchInto(OShareParameter.class);
	}
	
	public int insert(Connection con, OShareParameter item) throws DAOException {
		DSLContext dsl = getDSL(con);
		ShareParametersRecord record = dsl.newRecord(SHARE_PARAMETERS, item);
		return dsl
			.insertInto(SHARE_PARAMETERS)
			.set(record)
			.execute();
	}
	
	public int update(Connection con, OShareParameter item) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.update(SHARE_PARAMETERS)
			.set(SHARE_PARAMETERS.VALUE, item.getValue())
			.where(
					SHARE_PARAMETERS.SHARE_ID.equal(item.getShareId())
					.and(SHARE_PARAMETERS.USER_UID.equal(item.getUserUid()))
			)
			.execute();
	}
	
	public int deleteByShareUser(Connection con, String shareId, String userUid) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.delete(SHARE_PARAMETERS)
			.where(
					SHARE_PARAMETERS.SHARE_ID.equal(shareId)
					.and(SHARE_PARAMETERS.USER_UID.equal(userUid))
			)
			.execute();
	}
}
