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

import com.sonicle.webtop.core.bol.OIMHistoryMessage;
import static com.sonicle.webtop.core.jooq.core.Sequences.SEQ_IM_HISTORY_MESSAGES;
import static com.sonicle.webtop.core.jooq.core.Tables.IM_HISTORY_MESSAGES;
import com.sonicle.webtop.core.sdk.UserProfileId;
import java.sql.Connection;
import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.jooq.DSLContext;

/**
 *
 * @author malbinola
 */
public class IMHistoryMessageDAO extends BaseDAO {
	private final static IMHistoryMessageDAO INSTANCE = new IMHistoryMessageDAO();
	public static IMHistoryMessageDAO getInstance() {
		return INSTANCE;
	}
	
	public Long getSequence(Connection con) throws DAOException {
		DSLContext dsl = getDSL(con);
		Long nextID = dsl.nextval(SEQ_IM_HISTORY_MESSAGES);
		return nextID;
	}
	
	public List<OIMHistoryMessage> selectByProfileChatDate(Connection con, UserProfileId profile, String chatJid, LocalDate date) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select(
				IM_HISTORY_MESSAGES.ID,
				IM_HISTORY_MESSAGES.DOMAIN_ID,
				IM_HISTORY_MESSAGES.CHAT_JID,
				IM_HISTORY_MESSAGES.SENDER_JID,
				IM_HISTORY_MESSAGES.SENDER_RESOURCE,
				IM_HISTORY_MESSAGES.DATE,
				IM_HISTORY_MESSAGES.TIMESTAMP,
				IM_HISTORY_MESSAGES.ACTION,
				IM_HISTORY_MESSAGES.TEXT,
				IM_HISTORY_MESSAGES.MESSAGE_UID,
				IM_HISTORY_MESSAGES.STANZA_ID
			)
			.from(IM_HISTORY_MESSAGES)
			.where(
				IM_HISTORY_MESSAGES.DOMAIN_ID.equal(profile.getDomainId())
					.and(IM_HISTORY_MESSAGES.USER_ID.equal(profile.getUserId()))
				.and(IM_HISTORY_MESSAGES.CHAT_JID.equal(chatJid))
				.and(IM_HISTORY_MESSAGES.DATE.equal(date))
			)
			.orderBy(
				IM_HISTORY_MESSAGES.TIMESTAMP.asc()
			)
			.fetchInto(OIMHistoryMessage.class);
	}
	
	/*
	public List<OIMHistoryMessage> selectByProfileChatDates(Connection con, UserProfileId profile, String chatJid, DateTime fromDate, DateTime toDate) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select(
				IM_HISTORY_MESSAGES.ID,
				IM_HISTORY_MESSAGES.DOMAIN_ID,
				IM_HISTORY_MESSAGES.CHAT_JID,
				IM_HISTORY_MESSAGES.SENDER_JID,
				IM_HISTORY_MESSAGES.SENDER_RESOURCE,
				IM_HISTORY_MESSAGES.DATE,
				IM_HISTORY_MESSAGES.TIMESTAMP,
				IM_HISTORY_MESSAGES.ACTION,
				IM_HISTORY_MESSAGES.TEXT,
				IM_HISTORY_MESSAGES.MESSAGE_UID,
				IM_HISTORY_MESSAGES.STANZA_ID
			)
			.from(IM_HISTORY_MESSAGES)
			.where(
				IM_HISTORY_MESSAGES.DOMAIN_ID.equal(profile.getDomainId())
					.and(IM_HISTORY_MESSAGES.USER_ID.equal(profile.getUserId()))
				.and(IM_HISTORY_MESSAGES.CHAT_JID.equal(chatJid))
				.and(IM_HISTORY_MESSAGES.TIMESTAMP.greaterOrEqual(fromDate)
					.and(IM_HISTORY_MESSAGES.TIMESTAMP.lessThan(toDate)))
			)
			.orderBy(
				IM_HISTORY_MESSAGES.TIMESTAMP.asc()
			)
			.fetchInto(OIMHistoryMessage.class);
	}
	*/
	
	public int insert(Connection con, OIMHistoryMessage item) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.insertInto(IM_HISTORY_MESSAGES)
			.set(IM_HISTORY_MESSAGES.DOMAIN_ID, item.getDomainId())
			.set(IM_HISTORY_MESSAGES.USER_ID, item.getUserId())
			.set(IM_HISTORY_MESSAGES.CHAT_JID, item.getChatJid())
			.set(IM_HISTORY_MESSAGES.SENDER_JID, item.getSenderJid())
			.set(IM_HISTORY_MESSAGES.SENDER_RESOURCE, item.getSenderResource())
			.set(IM_HISTORY_MESSAGES.DATE, item.getDate())
			.set(IM_HISTORY_MESSAGES.TIMESTAMP, item.getTimestamp())
			.set(IM_HISTORY_MESSAGES.ACTION, item.getAction())
			.set(IM_HISTORY_MESSAGES.TEXT, item.getText())
			.set(IM_HISTORY_MESSAGES.MESSAGE_UID, item.getMessageUid())
			.set(IM_HISTORY_MESSAGES.STANZA_ID, item.getStanzaId())
			.execute();
	}
	
	public int deleteByDomain(Connection con, String domainId) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.delete(IM_HISTORY_MESSAGES)
			.where(
				IM_HISTORY_MESSAGES.DOMAIN_ID.equal(domainId)
			)
			.execute();
	}
	
	public int deleteByProfile(Connection con, UserProfileId profile) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.delete(IM_HISTORY_MESSAGES)
			.where(
				IM_HISTORY_MESSAGES.DOMAIN_ID.equal(profile.getDomainId())
					.and(IM_HISTORY_MESSAGES.USER_ID.equal(profile.getUserId()))
			)
			.execute();
	}
	
	public int deleteByProfileChat(Connection con, UserProfileId profile, String chatJid) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.delete(IM_HISTORY_MESSAGES)
			.where(
				IM_HISTORY_MESSAGES.DOMAIN_ID.equal(profile.getDomainId())
					.and(IM_HISTORY_MESSAGES.USER_ID.equal(profile.getUserId()))
				.and(IM_HISTORY_MESSAGES.CHAT_JID.equal(chatJid))
			)
			.execute();
	}
}
