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
import com.sonicle.webtop.core.bol.VActivity;
import com.sonicle.webtop.core.bol.OActivity;
import static com.sonicle.webtop.core.jooq.core.Sequences.SEQ_ACTIVITIES;
import static com.sonicle.webtop.core.jooq.core.Tables.*;
import com.sonicle.webtop.core.jooq.core.tables.records.ActivitiesRecord;
import com.sonicle.webtop.core.model.Activity;
import java.sql.Connection;
import java.util.Collection;
import java.util.List;
import org.jooq.DSLContext;

/**
 *
 * @author malbinola
 */
public class ActivityDAO extends BaseDAO {
	private final static ActivityDAO INSTANCE = new ActivityDAO();

	public static ActivityDAO getInstance() {
		return INSTANCE;
	}

	public Long getSequence(Connection con) throws DAOException {
		DSLContext dsl = getDSL(con);
		Long nextID = dsl.nextval(SEQ_ACTIVITIES);
		return nextID;
	}
	
	public List<OActivity> selectLiveByDomain(Connection con, String domainId) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select()
			.from(ACTIVITIES)
			.where(
				ACTIVITIES.DOMAIN_ID.equal(domainId)
			)
			.and(
				ACTIVITIES.REVISION_STATUS.notEqual(EnumUtils.toSerializedName(Activity.RevisionStatus.DELETED))
				.or(ACTIVITIES.REVISION_STATUS.isNull())
			)
			.orderBy(
				ACTIVITIES.DESCRIPTION.asc()
			)
			.fetchInto(OActivity.class);
	}
	
	public List<OActivity> selectLiveByDomainUser(Connection con, String domainId, String userId) throws DAOException {
		DSLContext dsl = getDSL(con);
		
		return dsl
			.select()
			.from(ACTIVITIES)
			.where(
				ACTIVITIES.DOMAIN_ID.equal(domainId)
				.and(ACTIVITIES.USER_ID.equal("*"))
				.or(
					ACTIVITIES.DOMAIN_ID.equal(domainId)
					.and(ACTIVITIES.USER_ID.equal(userId))
				)
			)
			.and(
				ACTIVITIES.REVISION_STATUS.notEqual(EnumUtils.toSerializedName(Activity.RevisionStatus.DELETED))
				.or(ACTIVITIES.REVISION_STATUS.isNull())
			)
			.orderBy(
				ACTIVITIES.DESCRIPTION.asc()
			)
			.fetchInto(OActivity.class);
	}
	
	public OActivity select(Connection con, Integer activityId) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select()
			.from(ACTIVITIES)
			.where(
					ACTIVITIES.ACTIVITY_ID.equal(activityId)
			)
			.fetchOneInto(OActivity.class);
	}
	
	public int insert(Connection con, OActivity item) throws DAOException {
		DSLContext dsl = getDSL(con);
		ActivitiesRecord record = dsl.newRecord(ACTIVITIES, item);
		return dsl
			.insertInto(ACTIVITIES)
			.set(record)
			.execute();
	}
	
	public int update(Connection con, OActivity item) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.update(ACTIVITIES)
			.set(ACTIVITIES.DOMAIN_ID, item.getDomainId())
			.set(ACTIVITIES.USER_ID, item.getUserId())
			.set(ACTIVITIES.DESCRIPTION, item.getDescription())
			.set(ACTIVITIES.READ_ONLY, item.getReadOnly())
			.set(ACTIVITIES.EXTERNAL_ID, item.getExternalId())
			.where(
				ACTIVITIES.ACTIVITY_ID.equal(item.getActivityId())
			)
			.execute();
	}
	
	public int logicDelete(Connection con, Integer activityId) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.update(ACTIVITIES)
			.set(ACTIVITIES.REVISION_STATUS, EnumUtils.toSerializedName(Activity.RevisionStatus.DELETED))
			.where(
				ACTIVITIES.ACTIVITY_ID.equal(activityId)
			)
			.execute();
	}
	
	public int deleteByDomain(Connection con, String domainId) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.delete(ACTIVITIES)
			.where(
				ACTIVITIES.DOMAIN_ID.equal(domainId)
			)
			.execute();
	}
}
