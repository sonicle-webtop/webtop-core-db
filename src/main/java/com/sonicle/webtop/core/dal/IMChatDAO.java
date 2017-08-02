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
import com.sonicle.webtop.core.bol.OIMChat;
import static com.sonicle.webtop.core.jooq.core.Sequences.SEQ_IM_CHATS;
import static com.sonicle.webtop.core.jooq.core.Tables.*;
import com.sonicle.webtop.core.jooq.core.tables.records.ImChatsRecord;
import com.sonicle.webtop.core.model.IMChat;
import com.sonicle.webtop.core.sdk.UserProfileId;
import java.sql.Connection;
import java.util.Collection;
import java.util.List;
import org.joda.time.DateTime;
import org.jooq.DSLContext;

/**
 *
 * @author malbinola
 */
public class IMChatDAO extends BaseDAO {
	private final static IMChatDAO INSTANCE = new IMChatDAO();
	public static IMChatDAO getInstance() {
		return INSTANCE;
	}
	
	public Long getSequence(Connection con) throws DAOException {
		DSLContext dsl = getDSL(con);
		Long nextID = dsl.nextval(SEQ_IM_CHATS);
		return nextID;
	}
	
	public OIMChat selectAliveByProfileChat(Connection con, UserProfileId profile, String chatJid) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select(
				IM_CHATS.ID,
				IM_CHATS.DOMAIN_ID,
				IM_CHATS.USER_ID,
				IM_CHATS.REVISION_STATUS,
				IM_CHATS.REVISION_TIMESTAMP,
				IM_CHATS.CHAT_JID,
				IM_CHATS.IS_GROUP_CHAT,
				IM_CHATS.OWNER_JID,
				IM_CHATS.NAME,
				IM_CHATS.LAST_SEEN_ACTIVITY,
				IM_CHATS.WITH_JID
			)
			.from(IM_CHATS)
			.where(
				IM_CHATS.DOMAIN_ID.equal(profile.getDomainId())
				.and(IM_CHATS.USER_ID.equal(profile.getUserId()))
				.and(
					IM_CHATS.REVISION_STATUS.notEqual(EnumUtils.toSerializedName(IMChat.RevisionStatus.DELETED))
				)
				.and(IM_CHATS.CHAT_JID.equal(chatJid))
			)
			.fetchOneInto(OIMChat.class);
	}
	
	public List<OIMChat> selectByProfileRevStatus(Connection con, UserProfileId profile, Collection<String> revStatuses) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select(
				IM_CHATS.ID,
				IM_CHATS.DOMAIN_ID,
				IM_CHATS.USER_ID,
				IM_CHATS.REVISION_STATUS,
				IM_CHATS.REVISION_TIMESTAMP,
				IM_CHATS.CHAT_JID,
				IM_CHATS.IS_GROUP_CHAT,
				IM_CHATS.OWNER_JID,
				IM_CHATS.NAME,
				IM_CHATS.LAST_SEEN_ACTIVITY,
				IM_CHATS.WITH_JID
			)
			.from(IM_CHATS)
			.where(
				IM_CHATS.DOMAIN_ID.equal(profile.getDomainId())
				.and(IM_CHATS.USER_ID.equal(profile.getUserId()))
				.and(
					IM_CHATS.REVISION_STATUS.in(revStatuses)
				)
			)
			.fetchInto(OIMChat.class);
	}
	
	public int insert(Connection con, OIMChat item, DateTime revisionTimestamp) throws DAOException {
		DSLContext dsl = getDSL(con);
		item.setRevisionStatus(EnumUtils.toSerializedName(IMChat.RevisionStatus.MODIFIED));
		item.setRevisionTimestamp(revisionTimestamp);
		ImChatsRecord record = dsl.newRecord(IM_CHATS, item);
		return dsl
			.insertInto(IM_CHATS)
			.set(record)
			.execute();
	}
	
	public int updateNameByProfileChat(Connection con, UserProfileId profile, String chatJid, String name, DateTime revisionTimestamp) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.update(IM_CHATS)
			.set(IM_CHATS.REVISION_TIMESTAMP, revisionTimestamp)
			.set(IM_CHATS.NAME, name)
			.where(
				IM_CHATS.DOMAIN_ID.equal(profile.getDomainId())
					.and(IM_CHATS.USER_ID.equal(profile.getUserId()))
				.and(IM_CHATS.CHAT_JID.equal(chatJid))
			)
			.execute();
	}
	
	public int updateLastActivityByProfileChat(Connection con, UserProfileId profile, String chatJid, DateTime lastActivity, DateTime revisionTimestamp) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.update(IM_CHATS)
			.set(IM_CHATS.REVISION_TIMESTAMP, revisionTimestamp)
			.set(IM_CHATS.LAST_SEEN_ACTIVITY, lastActivity)
			.where(
				IM_CHATS.DOMAIN_ID.equal(profile.getDomainId())
					.and(IM_CHATS.USER_ID.equal(profile.getUserId()))
				.and(IM_CHATS.CHAT_JID.equal(chatJid))
			)
			.execute();
	}
	
	public int updateRevisionStatusByProfileChat(Connection con, UserProfileId profile, String chatJid, DateTime revisionTimestamp, IMChat.RevisionStatus revisionStatus) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.update(IM_CHATS)
			.set(IM_CHATS.REVISION_STATUS, EnumUtils.toSerializedName(revisionStatus))
			.set(IM_CHATS.REVISION_TIMESTAMP, revisionTimestamp)
			.where(
				IM_CHATS.DOMAIN_ID.equal(profile.getDomainId())
					.and(IM_CHATS.USER_ID.equal(profile.getUserId()))
				.and(IM_CHATS.CHAT_JID.equal(chatJid))
			)
			.execute();
	}
	
	public int logicDeleteByProfileChat(Connection con, UserProfileId profile, String chatJid, DateTime revisionTimestamp) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.update(IM_CHATS)
			.set(IM_CHATS.REVISION_STATUS, EnumUtils.toSerializedName(IMChat.RevisionStatus.DELETED))
			.set(IM_CHATS.REVISION_TIMESTAMP, revisionTimestamp)
			.where(
				IM_CHATS.DOMAIN_ID.equal(profile.getDomainId())
					.and(IM_CHATS.USER_ID.equal(profile.getUserId()))
				.and(IM_CHATS.CHAT_JID.equal(chatJid))
			)
			.execute();
	}
	
	public int deleteByDomain(Connection con, String domainId) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.delete(IM_CHATS)
			.where(
				IM_CHATS.DOMAIN_ID.equal(domainId)
			)
			.execute();
	}
	
	public int deleteByProfile(Connection con, UserProfileId profile) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.delete(IM_CHATS)
			.where(
				IM_CHATS.DOMAIN_ID.equal(profile.getDomainId())
				.and(IM_CHATS.USER_ID.equal(profile.getUserId()))
			)
			.execute();
	}
	
	/*
	public int update(Connection con, OIMChat item) throws DAOException {
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
	*/
}
