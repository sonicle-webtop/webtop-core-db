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

import com.sonicle.webtop.core.bol.OIMMessage;
import static com.sonicle.webtop.core.jooq.core.Sequences.SEQ_IM_MESSAGES;
import static com.sonicle.webtop.core.jooq.core.Tables.IM_MESSAGES;
import com.sonicle.webtop.core.sdk.UserProfileId;
import java.sql.Connection;
import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.impl.DSL;

/**
 *
 * @author malbinola
 */
public class IMMessageDAO extends BaseDAO {
	private final static IMMessageDAO INSTANCE = new IMMessageDAO();
	public static IMMessageDAO getInstance() {
		return INSTANCE;
	}
	
	public Long getSequence(Connection con) throws DAOException {
		DSLContext dsl = getDSL(con);
		Long nextID = dsl.nextval(SEQ_IM_MESSAGES);
		return nextID;
	}
	
	public List<OIMMessage> findByProfileChat(Connection con, UserProfileId profile, String chatJid) throws DAOException {
		DSLContext dsl = getDSL(con);
		final Field<LocalDate> DATE = DSL.field("{0} AT TIME ZONE 'UTC'", LocalDate.class, IM_MESSAGES.TIMESTAMP).as("date");
		return dsl
			.select(
				IM_MESSAGES.ID,
				IM_MESSAGES.DOMAIN_ID,
				IM_MESSAGES.CHAT_JID,
				IM_MESSAGES.SENDER_JID,
				IM_MESSAGES.SENDER_RESOURCE,
				DATE,
				IM_MESSAGES.TIMESTAMP,
				IM_MESSAGES.DELIVERY_TIMESTAMP,
				IM_MESSAGES.ACTION,
				IM_MESSAGES.TEXT,
				IM_MESSAGES.DATA,
				IM_MESSAGES.MESSAGE_UID,
				IM_MESSAGES.STANZA_ID
			)
			.from(IM_MESSAGES)
			.where(
				IM_MESSAGES.DOMAIN_ID.equal(profile.getDomainId())
					.and(IM_MESSAGES.USER_ID.equal(profile.getUserId()))
				.and(IM_MESSAGES.CHAT_JID.equal(chatJid))
			)
			.orderBy(
				DATE.desc(),
				IM_MESSAGES.TIMESTAMP.asc(),
				IM_MESSAGES.ID.asc()
			)
			.fetchInto(OIMMessage.class);
	}
	
	public List<OIMMessage> findByProfileChatLike(Connection con, UserProfileId profile, String chatJid, String likeText) throws DAOException {
		DSLContext dsl = getDSL(con);
		final Field<LocalDate> DATE = DSL.field("{0} AT TIME ZONE 'UTC'", LocalDate.class, IM_MESSAGES.TIMESTAMP).as("date");
		return dsl
			.select(
				IM_MESSAGES.ID,
				IM_MESSAGES.DOMAIN_ID,
				IM_MESSAGES.CHAT_JID,
				IM_MESSAGES.SENDER_JID,
				IM_MESSAGES.SENDER_RESOURCE,
				DATE,
				IM_MESSAGES.TIMESTAMP,
				IM_MESSAGES.DELIVERY_TIMESTAMP,
				IM_MESSAGES.ACTION,
				IM_MESSAGES.TEXT,
				IM_MESSAGES.DATA,
				IM_MESSAGES.MESSAGE_UID,
				IM_MESSAGES.STANZA_ID
			)
			.from(IM_MESSAGES)
			.where(
				IM_MESSAGES.DOMAIN_ID.equal(profile.getDomainId())
					.and(IM_MESSAGES.USER_ID.equal(profile.getUserId()))
				.and(IM_MESSAGES.CHAT_JID.equal(chatJid))
				.and(IM_MESSAGES.TEXT.likeIgnoreCase(likeText))
			)
			.orderBy(
				DATE.desc(),
				IM_MESSAGES.TIMESTAMP.asc(),
				IM_MESSAGES.ID.asc()
			)
			.fetchInto(OIMMessage.class);
	}
	
	public List<String> selectStanzaIDsByProfileChat(Connection con, UserProfileId profile, String chatJid) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select(
				IM_MESSAGES.STANZA_ID
			)
			.from(IM_MESSAGES)
			.where(
				IM_MESSAGES.DOMAIN_ID.equal(profile.getDomainId())
					.and(IM_MESSAGES.USER_ID.equal(profile.getUserId()))
				.and(IM_MESSAGES.CHAT_JID.equal(chatJid))
			)
			.orderBy(
				IM_MESSAGES.ID.asc()
			)
			.fetchInto(String.class);
	}
	
	public List<DateTime> selectDatesByProfileChatYear(Connection con, UserProfileId profile, String chatJid, int year, DateTimeZone timezone) throws DAOException {
		DSLContext dsl = getDSL(con);
		final DateTime ts1 = new DateTime(year, 1, 1, 0, 0, 0, timezone);
		final DateTime ts2 = ts1.plusYears(1);
		return dsl
			.selectDistinct(
				IM_MESSAGES.TIMESTAMP
			)
			.from(IM_MESSAGES)
			.where(
				IM_MESSAGES.DOMAIN_ID.equal(profile.getDomainId())
					.and(IM_MESSAGES.USER_ID.equal(profile.getUserId()))
				.and(IM_MESSAGES.CHAT_JID.equal(chatJid))
				.and(timestampCondition(false, ts1, ts2))
			)
			.orderBy(
				IM_MESSAGES.TIMESTAMP.asc()
			)
			.fetchInto(DateTime.class);
	}
	
	private Condition timestampCondition(boolean byDelivery, DateTime greaterOrEqual, DateTime lessThan) {
		if (byDelivery) {
			return IM_MESSAGES.DELIVERY_TIMESTAMP.greaterOrEqual(greaterOrEqual)
				.and(IM_MESSAGES.DELIVERY_TIMESTAMP.lessThan(lessThan));
		} else {
			return IM_MESSAGES.TIMESTAMP.greaterOrEqual(greaterOrEqual)
				.and(IM_MESSAGES.TIMESTAMP.lessThan(lessThan));
		}
	}
	
	public List<OIMMessage> selectByProfileChatDate(Connection con, UserProfileId profile, String chatJid, LocalDate date, DateTimeZone timezone, boolean byDelivery) throws DAOException {
		DSLContext dsl = getDSL(con);
		final DateTime ts1 = date.toDateTime(LocalTime.MIDNIGHT, timezone);
		final DateTime ts2 = ts1.plusDays(1);
		
		return dsl
			.select(
				IM_MESSAGES.ID,
				IM_MESSAGES.DOMAIN_ID,
				IM_MESSAGES.CHAT_JID,
				IM_MESSAGES.SENDER_JID,
				IM_MESSAGES.SENDER_RESOURCE,
				IM_MESSAGES.TIMESTAMP,
				IM_MESSAGES.DELIVERY_TIMESTAMP,
				IM_MESSAGES.ACTION,
				IM_MESSAGES.TEXT,
				IM_MESSAGES.DATA,
				IM_MESSAGES.MESSAGE_UID,
				IM_MESSAGES.STANZA_ID
			)
			.from(IM_MESSAGES)
			.where(
				IM_MESSAGES.DOMAIN_ID.equal(profile.getDomainId())
					.and(IM_MESSAGES.USER_ID.equal(profile.getUserId()))
				.and(IM_MESSAGES.CHAT_JID.equal(chatJid))
				.and(timestampCondition(byDelivery, ts1, ts2))
			)
			.orderBy(
				IM_MESSAGES.TIMESTAMP.asc(),
				IM_MESSAGES.ID.asc()
			)
			.fetchInto(OIMMessage.class);
	}
	
	public int insert(Connection con, OIMMessage item) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.insertInto(IM_MESSAGES)
			.set(IM_MESSAGES.DOMAIN_ID, item.getDomainId())
			.set(IM_MESSAGES.USER_ID, item.getUserId())
			.set(IM_MESSAGES.CHAT_JID, item.getChatJid())
			.set(IM_MESSAGES.SENDER_JID, item.getSenderJid())
			.set(IM_MESSAGES.SENDER_RESOURCE, item.getSenderResource())
			.set(IM_MESSAGES.TIMESTAMP, item.getTimestamp())
			.set(IM_MESSAGES.DELIVERY_TIMESTAMP, item.getDeliveryTimestamp())
			.set(IM_MESSAGES.ACTION, item.getAction())
			.set(IM_MESSAGES.TEXT, item.getText())
			.set(IM_MESSAGES.DATA, item.getData())
			.set(IM_MESSAGES.MESSAGE_UID, item.getMessageUid())
			.set(IM_MESSAGES.STANZA_ID, item.getStanzaId())
			.execute();
	}
	
	public int deleteByDomain(Connection con, String domainId) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.delete(IM_MESSAGES)
			.where(
				IM_MESSAGES.DOMAIN_ID.equal(domainId)
			)
			.execute();
	}
	
	public int deleteByProfile(Connection con, UserProfileId profile) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.delete(IM_MESSAGES)
			.where(
				IM_MESSAGES.DOMAIN_ID.equal(profile.getDomainId())
					.and(IM_MESSAGES.USER_ID.equal(profile.getUserId()))
			)
			.execute();
	}
	
	public int deleteByProfileChat(Connection con, UserProfileId profile, String chatJid) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.delete(IM_MESSAGES)
			.where(
				IM_MESSAGES.DOMAIN_ID.equal(profile.getDomainId())
					.and(IM_MESSAGES.USER_ID.equal(profile.getUserId()))
				.and(IM_MESSAGES.CHAT_JID.equal(chatJid))
			)
			.execute();
	}
}
