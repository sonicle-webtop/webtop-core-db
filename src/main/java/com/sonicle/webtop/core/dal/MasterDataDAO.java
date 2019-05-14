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
import com.sonicle.webtop.core.bol.OMasterData;
import static com.sonicle.webtop.core.jooq.core.Tables.MASTER_DATA;
import com.sonicle.webtop.core.model.MasterData;
import java.sql.Connection;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

/**
 *
 * @author malbinola
 */
public class MasterDataDAO extends BaseDAO {
	private final static MasterDataDAO INSTANCE = new MasterDataDAO();
	public static MasterDataDAO getInstance() {
		return INSTANCE;
	}
	
	public OMasterData selectByDomainId(Connection con, String domainId, String masterDataId) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select(
				MASTER_DATA.DOMAIN_ID,
				MASTER_DATA.MASTER_DATA_ID,
				MASTER_DATA.PARENT_MASTER_DATA_ID,
				MASTER_DATA.EXTERNAL_ID,
				MASTER_DATA.TYPE,
				MASTER_DATA.REVISION_STATUS,
				MASTER_DATA.REVISION_TIMESTAMP,
				MASTER_DATA.LOCK_STATUS,
				MASTER_DATA.DESCRIPTION,
				MASTER_DATA.ADDRESS,
				MASTER_DATA.CITY,
				MASTER_DATA.POSTAL_CODE,
				MASTER_DATA.STATE,
				MASTER_DATA.COUNTRY,
				MASTER_DATA.TELEPHONE,
				MASTER_DATA.FAX,
				MASTER_DATA.MOBILE,
				MASTER_DATA.EMAIL,
				MASTER_DATA.NOTES
			)
			.from(MASTER_DATA)
			.where(
				MASTER_DATA.DOMAIN_ID.equal(domainId)
				.and(MASTER_DATA.MASTER_DATA_ID.equal(masterDataId))
			)
			.fetchOneInto(OMasterData.class);
	}
	
	public List<OMasterData> viewByDomainIn(Connection con, String domainId, Collection<String> masterDataIds) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select(
				MASTER_DATA.DOMAIN_ID,
				MASTER_DATA.MASTER_DATA_ID,
				MASTER_DATA.PARENT_MASTER_DATA_ID,
				MASTER_DATA.EXTERNAL_ID,
				MASTER_DATA.TYPE,
				MASTER_DATA.REVISION_STATUS,
				MASTER_DATA.REVISION_TIMESTAMP,
				MASTER_DATA.LOCK_STATUS,
				MASTER_DATA.DESCRIPTION
			)
			.from(MASTER_DATA)
			.where(
				MASTER_DATA.MASTER_DATA_ID.in(masterDataIds)
				.and(MASTER_DATA.DOMAIN_ID.equal(domainId))
				.and(MASTER_DATA.REVISION_STATUS.notEqual(EnumUtils.toSerializedName(MasterData.RevisionStatus.DELETED)))
			)
			.orderBy(
				MASTER_DATA.TYPE.asc(),
				MASTER_DATA.DESCRIPTION.asc()
			)
			.fetchInto(OMasterData.class);
	}
	
	public List<OMasterData> viewByIdsDomain(Connection con, Collection<String> masterDataIds, String domainId) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select(
				MASTER_DATA.DOMAIN_ID,
				MASTER_DATA.MASTER_DATA_ID,
				MASTER_DATA.PARENT_MASTER_DATA_ID,
				MASTER_DATA.EXTERNAL_ID,
				MASTER_DATA.TYPE,
				MASTER_DATA.REVISION_STATUS,
				MASTER_DATA.REVISION_TIMESTAMP,
				MASTER_DATA.LOCK_STATUS,
				MASTER_DATA.DESCRIPTION,
				MASTER_DATA.ADDRESS,
				MASTER_DATA.CITY,
				MASTER_DATA.POSTAL_CODE,
				MASTER_DATA.STATE,
				MASTER_DATA.COUNTRY
			)
			.from(MASTER_DATA)
			.where(
				MASTER_DATA.MASTER_DATA_ID.in(masterDataIds)
				.and(MASTER_DATA.DOMAIN_ID.equal(domainId))
				.and(MASTER_DATA.REVISION_STATUS.notEqual(EnumUtils.toSerializedName(MasterData.RevisionStatus.DELETED)))
			)
			.orderBy(
				MASTER_DATA.TYPE.asc(),
				MASTER_DATA.DESCRIPTION.asc()
			)
			.fetchInto(OMasterData.class);
	}
	
	public List<OMasterData> viewParentsByDomainTypePattern(Connection con, String domainId, Collection<String> types) throws DAOException {
		return viewParentsByDomainTypePattern(con, domainId, types, null);
	}
	
	public List<OMasterData> viewParentsByDomainTypePattern(Connection con, String domainId, Collection<String> types, String patternDescription) throws DAOException {
		DSLContext dsl = getDSL(con);
		
		Condition patternCndt = DSL.trueCondition();
		if (!StringUtils.isBlank(patternDescription)) {
			patternCndt = MASTER_DATA.DESCRIPTION.likeIgnoreCase(patternDescription);
		}
		
		return dsl
			.select(
				MASTER_DATA.DOMAIN_ID,
				MASTER_DATA.MASTER_DATA_ID,
				MASTER_DATA.PARENT_MASTER_DATA_ID,
				MASTER_DATA.EXTERNAL_ID,
				MASTER_DATA.TYPE,
				MASTER_DATA.REVISION_STATUS,
				MASTER_DATA.REVISION_TIMESTAMP,
				MASTER_DATA.LOCK_STATUS,
				MASTER_DATA.DESCRIPTION,
				MASTER_DATA.ADDRESS,
				MASTER_DATA.CITY,
				MASTER_DATA.POSTAL_CODE,
				MASTER_DATA.STATE,
				MASTER_DATA.COUNTRY
			)
			.from(MASTER_DATA)
			.where(
				MASTER_DATA.DOMAIN_ID.equal(domainId)
				.and(MASTER_DATA.PARENT_MASTER_DATA_ID.isNull())
				.and(MASTER_DATA.TYPE.in(types))
				.and(MASTER_DATA.REVISION_STATUS.notEqual(EnumUtils.toSerializedName(MasterData.RevisionStatus.DELETED))
				.and(patternCndt))
			)
			.orderBy(
				MASTER_DATA.TYPE.asc(),
				MASTER_DATA.DESCRIPTION.asc()
			)
			.fetchInto(OMasterData.class);
	}
	
	public List<OMasterData> viewChildrenByDomainType(Connection con, String domainId, Collection<String> types) throws DAOException {
		DSLContext dsl = getDSL(con);
		
		return dsl
			.select(
				MASTER_DATA.DOMAIN_ID,
				MASTER_DATA.MASTER_DATA_ID,
				MASTER_DATA.PARENT_MASTER_DATA_ID,
				MASTER_DATA.EXTERNAL_ID,
				MASTER_DATA.TYPE,
				MASTER_DATA.REVISION_STATUS,
				MASTER_DATA.REVISION_TIMESTAMP,
				MASTER_DATA.LOCK_STATUS,
				MASTER_DATA.DESCRIPTION,
				MASTER_DATA.ADDRESS,
				MASTER_DATA.CITY,
				MASTER_DATA.POSTAL_CODE,
				MASTER_DATA.STATE,
				MASTER_DATA.COUNTRY
			)
			.from(MASTER_DATA)
			.where(
				MASTER_DATA.DOMAIN_ID.equal(domainId)
				.and(MASTER_DATA.PARENT_MASTER_DATA_ID.isNotNull())
				.and(MASTER_DATA.TYPE.in(types))
				.and(MASTER_DATA.REVISION_STATUS.notEqual(EnumUtils.toSerializedName(MasterData.RevisionStatus.DELETED)))
			)
			.orderBy(
				MASTER_DATA.TYPE.asc(),
				MASTER_DATA.DESCRIPTION.asc()
			)
			.fetchInto(OMasterData.class);
	}
	
	public List<OMasterData> viewChildrenByDomainParentTypePattern(Connection con, String domainId, String parentMasterDataId, Collection<String> types) throws DAOException {
		return viewChildrenByDomainParentTypePattern(con, domainId, parentMasterDataId, types, null);
	}
	
	public List<OMasterData> viewChildrenByDomainParentTypePattern(Connection con, String domainId, String parentMasterDataId, Collection<String> types, String patternDescription) throws DAOException {
		DSLContext dsl = getDSL(con);
		
		Condition patternCndt = DSL.trueCondition();
		if (!StringUtils.isBlank(patternDescription)) {
			patternCndt = MASTER_DATA.DESCRIPTION.likeIgnoreCase(patternDescription);
		}
		
		return dsl
			.select(
				MASTER_DATA.DOMAIN_ID,
				MASTER_DATA.MASTER_DATA_ID,
				MASTER_DATA.PARENT_MASTER_DATA_ID,
				MASTER_DATA.EXTERNAL_ID,
				MASTER_DATA.TYPE,
				MASTER_DATA.REVISION_STATUS,
				MASTER_DATA.REVISION_TIMESTAMP,
				MASTER_DATA.LOCK_STATUS,
				MASTER_DATA.DESCRIPTION,
				MASTER_DATA.ADDRESS,
				MASTER_DATA.CITY,
				MASTER_DATA.POSTAL_CODE,
				MASTER_DATA.STATE,
				MASTER_DATA.COUNTRY
			)
			.from(MASTER_DATA)
			.where(
				MASTER_DATA.DOMAIN_ID.equal(domainId)
				.and(
					MASTER_DATA.MASTER_DATA_ID.equal(parentMasterDataId)
					.or(MASTER_DATA.PARENT_MASTER_DATA_ID.equal(parentMasterDataId))
				)
				.and(MASTER_DATA.TYPE.in(types))
				.and(MASTER_DATA.REVISION_STATUS.notEqual(EnumUtils.toSerializedName(MasterData.RevisionStatus.DELETED))
				.and(patternCndt))
			)
			.orderBy(
				MASTER_DATA.TYPE.asc(),
				MASTER_DATA.DESCRIPTION.asc()
			)
			.fetchInto(OMasterData.class);
	}
}
