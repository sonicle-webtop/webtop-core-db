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

import com.sonicle.commons.EnumUtils;
import com.sonicle.webtop.core.bol.VCausal;
import com.sonicle.webtop.core.bol.OCausal;
import static com.sonicle.webtop.core.jooq.core.Sequences.SEQ_CAUSALS;
import static com.sonicle.webtop.core.jooq.core.Tables.CAUSALS;
import static com.sonicle.webtop.core.jooq.core.Tables.MASTER_DATA;
import static com.sonicle.webtop.core.jooq.core.Tables.DOMAINS;
import static com.sonicle.webtop.core.jooq.core.Tables.USERS;

import com.sonicle.webtop.core.jooq.core.tables.records.CausalsRecord;
import com.sonicle.webtop.core.model.Causal;
import java.sql.Connection;
import java.util.Collection;
import java.util.List;
import org.jooq.DSLContext;

/**
 *
 * @author malbinola
 */
public class CausalDAO extends BaseDAO {
	private final static CausalDAO INSTANCE = new CausalDAO();
	public static CausalDAO getInstance() {
		return INSTANCE;
	}

	public Long getSequence(Connection con) throws DAOException {
		DSLContext dsl = getDSL(con);
		Long nextID = dsl.nextval(SEQ_CAUSALS);
		return nextID;
	}
	
	public List<VCausal> viewLiveByDomain(Connection con, String domainId) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select(
				CAUSALS.CAUSAL_ID,
				CAUSALS.DOMAIN_ID,
				CAUSALS.USER_ID,
				CAUSALS.MASTER_DATA_ID,
				CAUSALS.REVISION_STATUS,
				CAUSALS.DESCRIPTION,
				CAUSALS.READ_ONLY,
				CAUSALS.EXTERNAL_ID
			)
			.select(
				MASTER_DATA.DESCRIPTION.as("master_data_description")
			)
			.from(CAUSALS)
			.leftOuterJoin(MASTER_DATA).on(
				CAUSALS.MASTER_DATA_ID.equal(MASTER_DATA.MASTER_DATA_ID)
			)
			.where(
				CAUSALS.DOMAIN_ID.equal(domainId)
			)
			.and(
				CAUSALS.REVISION_STATUS.notEqual(EnumUtils.toSerializedName(Causal.RevisionStatus.DELETED))
				.or(CAUSALS.REVISION_STATUS.isNull())
			)
			.orderBy(
				CAUSALS.DESCRIPTION.asc()
			)
			.fetchInto(VCausal.class);
	}
	
	public List<OCausal> selectLiveByDomainUser(Connection con, String domainId, String userId) throws DAOException {
		DSLContext dsl = getDSL(con);
		
		return dsl
			.select()
			.from(CAUSALS)
			.where(
				CAUSALS.DOMAIN_ID.equal(domainId)
				.and(CAUSALS.USER_ID.equal("*"))
				.or(
					CAUSALS.DOMAIN_ID.equal(domainId)
					.and(CAUSALS.USER_ID.equal(userId))
				)
			)
			.and(
				CAUSALS.REVISION_STATUS.notEqual(EnumUtils.toSerializedName(Causal.RevisionStatus.DELETED))
				.or(CAUSALS.REVISION_STATUS.isNull())
			)
			.orderBy(
				CAUSALS.DESCRIPTION.asc()
			)
			.fetchInto(OCausal.class);
	}
	
	public List<OCausal> selectLiveByDomainUserMasterData(Connection con, String domainId, String userId, String masterDataId) throws DAOException {
		DSLContext dsl = getDSL(con);
		
		return dsl
			.select()
			.from(CAUSALS)
			.where(
				CAUSALS.DOMAIN_ID.equal(domainId)
				.and(CAUSALS.USER_ID.equal("*"))
				.or(
					CAUSALS.DOMAIN_ID.equal(domainId)
					.and(CAUSALS.USER_ID.equal(userId))
				)
			)
			.and(
				CAUSALS.MASTER_DATA_ID.equal(masterDataId)
				.or(CAUSALS.MASTER_DATA_ID.isNull())
			)
			.and(
				CAUSALS.REVISION_STATUS.notEqual(EnumUtils.toSerializedName(Causal.RevisionStatus.DELETED))
				.or(CAUSALS.REVISION_STATUS.isNull())
			)
			.orderBy(
				CAUSALS.DESCRIPTION.asc()
			)
			.fetchInto(OCausal.class);
	}
	
	public OCausal select(Connection con, Integer causalId) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select()
			.from(CAUSALS)
			.where(
				CAUSALS.CAUSAL_ID.equal(causalId)
			)
			.fetchOneInto(OCausal.class);
	}
	
	public int insert(Connection con, OCausal item) throws DAOException {
		DSLContext dsl = getDSL(con);
		CausalsRecord record = dsl.newRecord(CAUSALS, item);
		return dsl
			.insertInto(CAUSALS)
			.set(record)
			.execute();
	}
	
	public int update(Connection con, OCausal item) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.update(CAUSALS)
			.set(CAUSALS.DOMAIN_ID, item.getDomainId())
			.set(CAUSALS.USER_ID, item.getUserId())
			.set(CAUSALS.MASTER_DATA_ID, item.getMasterDataId())
			.set(CAUSALS.DESCRIPTION, item.getDescription())
			.set(CAUSALS.READ_ONLY, item.getReadOnly())
			.set(CAUSALS.EXTERNAL_ID, item.getExternalId())
			.where(
				CAUSALS.CAUSAL_ID.equal(item.getCausalId())
			)
			.execute();
	}
	
	public int logicDelete(Connection con, Integer causalId) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.update(CAUSALS)
			.set(CAUSALS.REVISION_STATUS, EnumUtils.toSerializedName(Causal.RevisionStatus.DELETED))
			.where(
				CAUSALS.CAUSAL_ID.equal(causalId)
			)
			.execute();
	}
	
	public int deleteByDomain(Connection con, String domainId) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.delete(CAUSALS)
			.where(
				CAUSALS.DOMAIN_ID.equal(domainId)
			)
			.execute();
	}
}
