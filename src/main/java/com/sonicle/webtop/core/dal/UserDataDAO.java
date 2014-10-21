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

import com.sonicle.webtop.core.bol.OUser;
import static com.sonicle.webtop.core.jooq.Tables.*;
import com.sonicle.webtop.core.jooq.tables.records.UsersRecord;
import java.sql.Connection;
import org.jooq.DSLContext;

/**
 *
 * @author gbulfon
 */
public class UserDataDAO extends BaseDAO {
	
	private final static UserDataDAO INSTANCE = new UserDataDAO();
	public static UserDataDAO getInstance() {
		return INSTANCE;
	}
	
	public OUser selectByDomainUser(Connection con, String domainId, String userId) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select(
				USERS.DOMAIN_ID,
				USERS.USER_ID,
				USERS.TITLE,
				USERS.FIRST_NAME,
				USERS.LAST_NAME,
				USERS.ADDRESS,
				USERS.POSTAL_CODE,
				USERS.CITY,
				USERS.STATE,
				USERS.COUNTRY,
				USERS.EMAIL,
				USERS.MOBILE,
				USERS.TELEPHONE,
				USERS.FAX,
				USERS.COMPANY,
				USERS.FUNCTION,
				USERS.WORK_EMAIL,
				USERS.WORK_MOBILE,
				USERS.WORK_TELEPHONE,
				USERS.WORK_FAX,
				USERS.CUSTOM_1,
				USERS.CUSTOM_2,
				USERS.CUSTOM_3
			).from(USERS)
			.where(
				USERS.DOMAIN_ID.equal(domainId)
				.and(USERS.USER_ID.equal(userId))
			)
			.fetchOneInto(OUser.class);
	}
	
	public int update(Connection con, OUser item) throws DAOException {
		DSLContext dsl = getDSL(con);
		UsersRecord record = dsl.newRecord(USERS, item);
		return dsl
			.update(USERS)
			.set(USERS.TITLE, item.getTitle())
			.set(USERS.FIRST_NAME, item.getFirstName())
			.set(USERS.LAST_NAME, item.getLastName())
			.set(USERS.ADDRESS, item.getAddress())
			.set(USERS.POSTAL_CODE, item.getPostalCode())
			.set(USERS.CITY, item.getCity())
			.set(USERS.STATE, item.getState())
			.set(USERS.COUNTRY, item.getCountry())
			.set(USERS.EMAIL, item.getEmail())
			.set(USERS.MOBILE, item.getMobile())
			.set(USERS.TELEPHONE, item.getTelephone())
			.set(USERS.FAX, item.getFax())
			.set(USERS.COMPANY, item.getCompany())
			.set(USERS.FUNCTION, item.getFunction())
			.set(USERS.WORK_EMAIL, item.getWorkEmail())
			.set(USERS.WORK_MOBILE, item.getWorkMobile())
			.set(USERS.WORK_TELEPHONE, item.getWorkTelephone())
			.set(USERS.WORK_FAX, item.getFax())
			.set(USERS.CUSTOM_1, item.getCustom_1())
			.set(USERS.CUSTOM_2, item.getCustom_2())
			.set(USERS.CUSTOM_3, item.getCustom_3())
			.where(
				USERS.DOMAIN_ID.equal(item.getDomainId())
				.and(USERS.USER_ID.equal(item.getUserId()))
			)
			.execute();
	}
}
