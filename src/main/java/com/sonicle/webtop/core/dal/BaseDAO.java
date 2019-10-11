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

import com.sonicle.commons.db.DbUtils;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.jooq.Condition;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.ExecuteContext;
import org.jooq.Field;
import org.jooq.RecordMapperProvider;
import org.jooq.SQLDialect;
import org.jooq.conf.MappedSchema;
import org.jooq.conf.RenderMapping;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultExecuteListener;
import org.jooq.impl.DefaultExecuteListenerProvider;

/**
 *
 * @author malbinola
 */
public class BaseDAO {
	
	public DSLContext getDSL(Connection con) {
		return getDSL(con, null, null);
	}
	
	public DSLContext getDSL(Connection con, RecordMapperProvider rmp) {
		return getDSL(con, null, rmp);
	}
	
	public DSLContext getDSL(Connection con, MappedSchema mappedSchema) {
		Settings settings = null;
		if (mappedSchema != null) {
			settings = new Settings()
					.withRenderMapping(
							new RenderMapping()
							.withSchemata(mappedSchema)
					);
		}
		return getDSL(con, settings, null);
	}
	
	public DSLContext getDSL(Connection con, Settings settings, RecordMapperProvider recordMapperProvider) {
		Configuration configuration = new DefaultConfiguration()
				.set(con)
				.set(createDialect(con))
				.set(new DefaultExecuteListenerProvider(new DAOExecuteListener()));
		if (settings != null) configuration.set(settings);
		if (recordMapperProvider != null) configuration.set(recordMapperProvider);
		return DSL.using(configuration);
	}
	
	public static SQLDialect createDialect(Connection con) {
		return SQLDialect.POSTGRES;
	}
	
	public static MappedSchema createMappedSchema(String mapFrom, String mapTo) {
		return new MappedSchema()
				.withInput(mapFrom)
				.withOutput(mapTo);
	}
	
	public static DateTime createRevisionTimestamp() {
		return DateTime.now(DateTimeZone.UTC);
	}
	
	public static Condition createCondition(com.github.rutledgepaulv.qbuilders.conditions.Condition<?> conditionPredicate, com.github.rutledgepaulv.qbuilders.visitors.ContextualNodeVisitor<? extends Condition, Void> visitor) {
		return (conditionPredicate != null) ? conditionPredicate.query(visitor) : DSL.trueCondition();
	}
	
	public static class FieldsMap extends HashMap<Field<?>, Object> {
		
		public FieldsMap() {
			super();
		}
	}
	
	public static class CrudInfo {
		public DateTime timestamp;
		public String device;
		public String user;
		
		/*
		public RevisionInfo() {
			this(null, null);
		}
		*/
		
		public CrudInfo(String device, String user) {
			this(DateTime.now(DateTimeZone.UTC), device, user);
		}
		
		public CrudInfo(DateTime timestamp, String device, String user) {
			this.timestamp = timestamp;
			this.device = device;
			this.user = user;
		}
	}
	
	public static class DAOExecuteListener extends DefaultExecuteListener {

		@Override
		public void exception(ExecuteContext ctx) {
			final SQLException sqlEx = ctx.sqlException();
			if ((sqlEx != null) && DbUtils.isIntegrityConstraintViolation(sqlEx)) {
				//SQLStateClass sqlState = SQLStateClass.fromCode(ctx.sqlException().getSQLState());
				ctx.exception(new DAOIntegrityViolationException("JOOQ", ctx.exception()));
			} else {
				ctx.exception(new DAOException("JOOQ", ctx.exception()));
			}
		}
	}
}
