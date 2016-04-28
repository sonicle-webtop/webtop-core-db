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

import com.sonicle.webtop.core.bol.OCustomer;
import java.sql.Connection;
import java.util.List;
import org.jooq.DSLContext;
import org.jooq.Field;
import static org.jooq.impl.DSL.*;

/**
 *
 * @author malbinola
 */
public class CustomerDAO extends BaseDAO {
	private final static CustomerDAO INSTANCE = new CustomerDAO();
	public static CustomerDAO getInstance() {
		return INSTANCE;
	}
	
	public static Field<String> CUSTOMER_ID = field("customer_id", String.class);
	public static Field<String> PARENT_ID = field("parent_id", String.class);
	public static Field<String> EXTERNAL_ID = field("external_id", String.class);
	public static Field<String> TYPE = field("type", String.class);
	public static Field<String> STATUS = field("status", String.class);
	public static Field<String> DOMAIN_ID = field("domain_id", String.class);
	public static Field<String> DESCRIPTION = field("description", String.class);
	public static Field<String> ADDRESS = field("address", String.class);
	public static Field<String> CITY = field("city", String.class);
	public static Field<String> STATE = field("state", String.class);
	public static Field<String> POSTALCODE = field("postalcode", String.class);
	public static Field<String> COUNTRTY = field("country", String.class);
	
	public String selectDescriptionById(Connection con, String customerId) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
				.select(
						DESCRIPTION
				)
				.from("public.customers")
				.where(
						CUSTOMER_ID.equal(customerId)
				)
				.fetchOne(0, String.class);
	}
	
	public OCustomer viewById(Connection con, String customerId) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
				.select(
						CUSTOMER_ID,
						PARENT_ID,
						EXTERNAL_ID,
						DESCRIPTION
				)
				.from("public.customers")
				.where(
						CUSTOMER_ID.equal(customerId)
				)
				.orderBy(
						DESCRIPTION.asc()
				)
				.fetchOneInto(OCustomer.class);
	}
	
	public List<OCustomer> viewByLike(Connection con, String likeDescription) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
				.select(
						CUSTOMER_ID,
						PARENT_ID,
						EXTERNAL_ID,
						TYPE,
						DESCRIPTION,
						ADDRESS,
						CITY,
						STATE,
						POSTALCODE,
						COUNTRTY
				)
				.from("public.customers")
				.where(
						DESCRIPTION.likeIgnoreCase(likeDescription)
						.and(
							STATUS.notEqual("D")
							.or(STATUS.isNull())
						)
				)
				.orderBy(
						DESCRIPTION.asc()
				)
				.fetchInto(OCustomer.class);
	}
	
	public List<OCustomer> viewByParentDomainLike(Connection con, String parentId, String domainId, String likeDescription) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
				.select(
						CUSTOMER_ID,
						PARENT_ID,
						EXTERNAL_ID,
						TYPE,
						DESCRIPTION,
						ADDRESS,
						CITY,
						STATE,
						POSTALCODE,
						COUNTRTY
				)
				.from("public.customers")
				.where(
						DESCRIPTION.likeIgnoreCase(likeDescription)
						.and(
							PARENT_ID.equal(parentId)
							.or(CUSTOMER_ID.equal(parentId))
						)
						.and(
							STATUS.notEqual("D")
							.or(STATUS.isNull())
						)
						.and(
							DOMAIN_ID.equal(domainId)
							.or(DOMAIN_ID.isNull())
						)
				)
				.orderBy(
						DESCRIPTION.asc()
				)
				.fetchInto(OCustomer.class);
	}
}
