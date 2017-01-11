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

import com.sonicle.webtop.core.bol.OUpgradeStatement;
import static com.sonicle.webtop.core.jooq.core.Sequences.SEQ_UPGRADE_STATEMENTS;
import static com.sonicle.webtop.core.jooq.core.Tables.UPGRADE_STATEMENTS;
import com.sonicle.webtop.core.jooq.core.tables.records.UpgradeStatementsRecord;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import org.jooq.DSLContext;
import static org.jooq.impl.DSL.*;

/**
 *
 * @author malbinola
 */
public class UpgradeStatementDAO extends BaseDAO {
	private final static UpgradeStatementDAO INSTANCE = new UpgradeStatementDAO();
	public static UpgradeStatementDAO getInstance() {
		return INSTANCE;
	}
	
	public Long getSequence(Connection con) throws DAOException {
		DSLContext dsl = getDSL(con);
		Long nextID = dsl.nextval(SEQ_UPGRADE_STATEMENTS);
		return nextID;
	}
	
	public Integer maxId(Connection con) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select(
					max(UPGRADE_STATEMENTS.UPGRADE_STATEMENT_ID)
			)
			.from(UPGRADE_STATEMENTS)
			.fetchOne(0, Integer.class);
	}
	
	public int countPendingByTagType(Connection con, String tag, String statementType) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.selectCount()
			.from(UPGRADE_STATEMENTS)
			.where(
				UPGRADE_STATEMENTS.TAG.equal(tag)
				.and(UPGRADE_STATEMENTS.STATEMENT_TYPE.equal(statementType))
				.and(UPGRADE_STATEMENTS.RUN_STATUS.isNull())
			)
			.fetchOne(0, Integer.class);
	}
	
	public String selectLastTag(Connection con) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select(
					max(UPGRADE_STATEMENTS.TAG)
			)
			.from(UPGRADE_STATEMENTS)
			.fetchOne(0, String.class);
	}
	
	public List<OUpgradeStatement> selectByTag(Connection con, String tag) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select()
			.from(UPGRADE_STATEMENTS)
			.where(
				UPGRADE_STATEMENTS.TAG.equal(tag)
			)
			.orderBy(
					UPGRADE_STATEMENTS.UPGRADE_STATEMENT_ID.asc()
			)
			.fetchInto(OUpgradeStatement.class);
	}
	
	public List<OUpgradeStatement> selectFromIdByTagService(Connection con, int fromId, String tag, String serviceId) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select()
			.from(UPGRADE_STATEMENTS)
			.where(
				UPGRADE_STATEMENTS.UPGRADE_STATEMENT_ID.greaterThan(fromId)
				.and(UPGRADE_STATEMENTS.TAG.equal(tag))
				.and(UPGRADE_STATEMENTS.SERVICE_ID.equal(serviceId))
			)
			.orderBy(
					UPGRADE_STATEMENTS.UPGRADE_STATEMENT_ID.asc()
			)
			.fetchInto(OUpgradeStatement.class);
	}
	
	public List<OUpgradeStatement> selectByTagService(Connection con, String tag, String serviceId) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select()
			.from(UPGRADE_STATEMENTS)
			.where(
				UPGRADE_STATEMENTS.TAG.equal(tag)
				.and(UPGRADE_STATEMENTS.SERVICE_ID.equal(serviceId))
			)
			.orderBy(
					UPGRADE_STATEMENTS.UPGRADE_STATEMENT_ID.asc()
			)
			.fetchInto(OUpgradeStatement.class);
	}
	
	public int update(Connection con, OUpgradeStatement item) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.update(UPGRADE_STATEMENTS)
			.set(UPGRADE_STATEMENTS.STATEMENT_BODY, item.getStatementBody())
			.set(UPGRADE_STATEMENTS.RUN_STATUS, item.getRunStatus())
			.set(UPGRADE_STATEMENTS.RUN_TIMESTAMP, item.getRunTimestamp())
			.set(UPGRADE_STATEMENTS.RUN_MESSAGE, item.getRunMessage())
			.where(
					UPGRADE_STATEMENTS.UPGRADE_STATEMENT_ID.equal(item.getUpgradeStatementId())
			)
			.execute();
	}
	
	public int insert(Connection con, OUpgradeStatement item) throws DAOException {
		DSLContext dsl = getDSL(con);
		UpgradeStatementsRecord record = dsl.newRecord(UPGRADE_STATEMENTS, item);
		return dsl
			.insertInto(UPGRADE_STATEMENTS)
			.set(record)
			.execute();
	}
	
	public int batchInsert(Connection con, List<OUpgradeStatement> items) throws DAOException {
		DSLContext dsl = getDSL(con);
		ArrayList<UpgradeStatementsRecord> records = new ArrayList<>();
		for(OUpgradeStatement item : items) {
			records.add(dsl.newRecord(UPGRADE_STATEMENTS, item));
		}
		dsl.batchInsert(records).execute();
		return items.size();
	}
}
