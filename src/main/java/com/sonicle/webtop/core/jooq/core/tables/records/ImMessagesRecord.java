/**
 * This class is generated by jOOQ
 */
package com.sonicle.webtop.core.jooq.core.tables.records;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.5.3"
	},
	comments = "This class is generated by jOOQ"
)
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ImMessagesRecord extends org.jooq.impl.UpdatableRecordImpl<com.sonicle.webtop.core.jooq.core.tables.records.ImMessagesRecord> implements org.jooq.Record13<java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, org.joda.time.LocalDate, org.joda.time.DateTime, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String> {

	private static final long serialVersionUID = 1585055537;

	/**
	 * Setter for <code>core.im_messages.id</code>.
	 */
	public void setId(java.lang.Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>core.im_messages.id</code>.
	 */
	public java.lang.Integer getId() {
		return (java.lang.Integer) getValue(0);
	}

	/**
	 * Setter for <code>core.im_messages.domain_id</code>.
	 */
	public void setDomainId(java.lang.String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>core.im_messages.domain_id</code>.
	 */
	public java.lang.String getDomainId() {
		return (java.lang.String) getValue(1);
	}

	/**
	 * Setter for <code>core.im_messages.user_id</code>.
	 */
	public void setUserId(java.lang.String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>core.im_messages.user_id</code>.
	 */
	public java.lang.String getUserId() {
		return (java.lang.String) getValue(2);
	}

	/**
	 * Setter for <code>core.im_messages.chat_jid</code>.
	 */
	public void setChatJid(java.lang.String value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>core.im_messages.chat_jid</code>.
	 */
	public java.lang.String getChatJid() {
		return (java.lang.String) getValue(3);
	}

	/**
	 * Setter for <code>core.im_messages.sender_jid</code>.
	 */
	public void setSenderJid(java.lang.String value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>core.im_messages.sender_jid</code>.
	 */
	public java.lang.String getSenderJid() {
		return (java.lang.String) getValue(4);
	}

	/**
	 * Setter for <code>core.im_messages.sender_resource</code>.
	 */
	public void setSenderResource(java.lang.String value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>core.im_messages.sender_resource</code>.
	 */
	public java.lang.String getSenderResource() {
		return (java.lang.String) getValue(5);
	}

	/**
	 * Setter for <code>core.im_messages.date</code>.
	 */
	public void setDate(org.joda.time.LocalDate value) {
		setValue(6, value);
	}

	/**
	 * Getter for <code>core.im_messages.date</code>.
	 */
	public org.joda.time.LocalDate getDate() {
		return (org.joda.time.LocalDate) getValue(6);
	}

	/**
	 * Setter for <code>core.im_messages.timestamp</code>.
	 */
	public void setTimestamp(org.joda.time.DateTime value) {
		setValue(7, value);
	}

	/**
	 * Getter for <code>core.im_messages.timestamp</code>.
	 */
	public org.joda.time.DateTime getTimestamp() {
		return (org.joda.time.DateTime) getValue(7);
	}

	/**
	 * Setter for <code>core.im_messages.action</code>.
	 */
	public void setAction(java.lang.String value) {
		setValue(8, value);
	}

	/**
	 * Getter for <code>core.im_messages.action</code>.
	 */
	public java.lang.String getAction() {
		return (java.lang.String) getValue(8);
	}

	/**
	 * Setter for <code>core.im_messages.text</code>.
	 */
	public void setText(java.lang.String value) {
		setValue(9, value);
	}

	/**
	 * Getter for <code>core.im_messages.text</code>.
	 */
	public java.lang.String getText() {
		return (java.lang.String) getValue(9);
	}

	/**
	 * Setter for <code>core.im_messages.message_uid</code>.
	 */
	public void setMessageUid(java.lang.String value) {
		setValue(10, value);
	}

	/**
	 * Getter for <code>core.im_messages.message_uid</code>.
	 */
	public java.lang.String getMessageUid() {
		return (java.lang.String) getValue(10);
	}

	/**
	 * Setter for <code>core.im_messages.stanza_id</code>.
	 */
	public void setStanzaId(java.lang.String value) {
		setValue(11, value);
	}

	/**
	 * Getter for <code>core.im_messages.stanza_id</code>.
	 */
	public java.lang.String getStanzaId() {
		return (java.lang.String) getValue(11);
	}

	/**
	 * Setter for <code>core.im_messages.data</code>.
	 */
	public void setData(java.lang.String value) {
		setValue(12, value);
	}

	/**
	 * Getter for <code>core.im_messages.data</code>.
	 */
	public java.lang.String getData() {
		return (java.lang.String) getValue(12);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Record1<java.lang.Integer> key() {
		return (org.jooq.Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record13 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row13<java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, org.joda.time.LocalDate, org.joda.time.DateTime, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String> fieldsRow() {
		return (org.jooq.Row13) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row13<java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, org.joda.time.LocalDate, org.joda.time.DateTime, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String> valuesRow() {
		return (org.jooq.Row13) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field1() {
		return com.sonicle.webtop.core.jooq.core.tables.ImMessages.IM_MESSAGES.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field2() {
		return com.sonicle.webtop.core.jooq.core.tables.ImMessages.IM_MESSAGES.DOMAIN_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field3() {
		return com.sonicle.webtop.core.jooq.core.tables.ImMessages.IM_MESSAGES.USER_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field4() {
		return com.sonicle.webtop.core.jooq.core.tables.ImMessages.IM_MESSAGES.CHAT_JID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field5() {
		return com.sonicle.webtop.core.jooq.core.tables.ImMessages.IM_MESSAGES.SENDER_JID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field6() {
		return com.sonicle.webtop.core.jooq.core.tables.ImMessages.IM_MESSAGES.SENDER_RESOURCE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<org.joda.time.LocalDate> field7() {
		return com.sonicle.webtop.core.jooq.core.tables.ImMessages.IM_MESSAGES.DATE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<org.joda.time.DateTime> field8() {
		return com.sonicle.webtop.core.jooq.core.tables.ImMessages.IM_MESSAGES.TIMESTAMP;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field9() {
		return com.sonicle.webtop.core.jooq.core.tables.ImMessages.IM_MESSAGES.ACTION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field10() {
		return com.sonicle.webtop.core.jooq.core.tables.ImMessages.IM_MESSAGES.TEXT;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field11() {
		return com.sonicle.webtop.core.jooq.core.tables.ImMessages.IM_MESSAGES.MESSAGE_UID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field12() {
		return com.sonicle.webtop.core.jooq.core.tables.ImMessages.IM_MESSAGES.STANZA_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field13() {
		return com.sonicle.webtop.core.jooq.core.tables.ImMessages.IM_MESSAGES.DATA;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value1() {
		return getId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value2() {
		return getDomainId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value3() {
		return getUserId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value4() {
		return getChatJid();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value5() {
		return getSenderJid();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value6() {
		return getSenderResource();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.joda.time.LocalDate value7() {
		return getDate();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.joda.time.DateTime value8() {
		return getTimestamp();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value9() {
		return getAction();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value10() {
		return getText();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value11() {
		return getMessageUid();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value12() {
		return getStanzaId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value13() {
		return getData();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ImMessagesRecord value1(java.lang.Integer value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ImMessagesRecord value2(java.lang.String value) {
		setDomainId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ImMessagesRecord value3(java.lang.String value) {
		setUserId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ImMessagesRecord value4(java.lang.String value) {
		setChatJid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ImMessagesRecord value5(java.lang.String value) {
		setSenderJid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ImMessagesRecord value6(java.lang.String value) {
		setSenderResource(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ImMessagesRecord value7(org.joda.time.LocalDate value) {
		setDate(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ImMessagesRecord value8(org.joda.time.DateTime value) {
		setTimestamp(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ImMessagesRecord value9(java.lang.String value) {
		setAction(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ImMessagesRecord value10(java.lang.String value) {
		setText(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ImMessagesRecord value11(java.lang.String value) {
		setMessageUid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ImMessagesRecord value12(java.lang.String value) {
		setStanzaId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ImMessagesRecord value13(java.lang.String value) {
		setData(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ImMessagesRecord values(java.lang.Integer value1, java.lang.String value2, java.lang.String value3, java.lang.String value4, java.lang.String value5, java.lang.String value6, org.joda.time.LocalDate value7, org.joda.time.DateTime value8, java.lang.String value9, java.lang.String value10, java.lang.String value11, java.lang.String value12, java.lang.String value13) {
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached ImMessagesRecord
	 */
	public ImMessagesRecord() {
		super(com.sonicle.webtop.core.jooq.core.tables.ImMessages.IM_MESSAGES);
	}

	/**
	 * Create a detached, initialised ImMessagesRecord
	 */
	public ImMessagesRecord(java.lang.Integer id, java.lang.String domainId, java.lang.String userId, java.lang.String chatJid, java.lang.String senderJid, java.lang.String senderResource, org.joda.time.LocalDate date, org.joda.time.DateTime timestamp, java.lang.String action, java.lang.String text, java.lang.String messageUid, java.lang.String stanzaId, java.lang.String data) {
		super(com.sonicle.webtop.core.jooq.core.tables.ImMessages.IM_MESSAGES);

		setValue(0, id);
		setValue(1, domainId);
		setValue(2, userId);
		setValue(3, chatJid);
		setValue(4, senderJid);
		setValue(5, senderResource);
		setValue(6, date);
		setValue(7, timestamp);
		setValue(8, action);
		setValue(9, text);
		setValue(10, messageUid);
		setValue(11, stanzaId);
		setValue(12, data);
	}
}
