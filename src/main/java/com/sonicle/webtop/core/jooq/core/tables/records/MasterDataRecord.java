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
public class MasterDataRecord extends org.jooq.impl.UpdatableRecordImpl<com.sonicle.webtop.core.jooq.core.tables.records.MasterDataRecord> implements org.jooq.Record14<java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, org.joda.time.DateTime, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String> {

	private static final long serialVersionUID = -1714762328;

	/**
	 * Setter for <code>core.master_data.domain_id</code>.
	 */
	public void setDomainId(java.lang.String value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>core.master_data.domain_id</code>.
	 */
	public java.lang.String getDomainId() {
		return (java.lang.String) getValue(0);
	}

	/**
	 * Setter for <code>core.master_data.master_data_id</code>.
	 */
	public void setMasterDataId(java.lang.String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>core.master_data.master_data_id</code>.
	 */
	public java.lang.String getMasterDataId() {
		return (java.lang.String) getValue(1);
	}

	/**
	 * Setter for <code>core.master_data.parent_master_data_id</code>.
	 */
	public void setParentMasterDataId(java.lang.String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>core.master_data.parent_master_data_id</code>.
	 */
	public java.lang.String getParentMasterDataId() {
		return (java.lang.String) getValue(2);
	}

	/**
	 * Setter for <code>core.master_data.external_id</code>.
	 */
	public void setExternalId(java.lang.String value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>core.master_data.external_id</code>.
	 */
	public java.lang.String getExternalId() {
		return (java.lang.String) getValue(3);
	}

	/**
	 * Setter for <code>core.master_data.type</code>.
	 */
	public void setType(java.lang.String value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>core.master_data.type</code>.
	 */
	public java.lang.String getType() {
		return (java.lang.String) getValue(4);
	}

	/**
	 * Setter for <code>core.master_data.revision_status</code>.
	 */
	public void setRevisionStatus(java.lang.String value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>core.master_data.revision_status</code>.
	 */
	public java.lang.String getRevisionStatus() {
		return (java.lang.String) getValue(5);
	}

	/**
	 * Setter for <code>core.master_data.revision_timestamp</code>.
	 */
	public void setRevisionTimestamp(org.joda.time.DateTime value) {
		setValue(6, value);
	}

	/**
	 * Getter for <code>core.master_data.revision_timestamp</code>.
	 */
	public org.joda.time.DateTime getRevisionTimestamp() {
		return (org.joda.time.DateTime) getValue(6);
	}

	/**
	 * Setter for <code>core.master_data.revision_sequence</code>.
	 */
	public void setRevisionSequence(java.lang.Integer value) {
		setValue(7, value);
	}

	/**
	 * Getter for <code>core.master_data.revision_sequence</code>.
	 */
	public java.lang.Integer getRevisionSequence() {
		return (java.lang.Integer) getValue(7);
	}

	/**
	 * Setter for <code>core.master_data.description</code>.
	 */
	public void setDescription(java.lang.String value) {
		setValue(8, value);
	}

	/**
	 * Getter for <code>core.master_data.description</code>.
	 */
	public java.lang.String getDescription() {
		return (java.lang.String) getValue(8);
	}

	/**
	 * Setter for <code>core.master_data.address</code>.
	 */
	public void setAddress(java.lang.String value) {
		setValue(9, value);
	}

	/**
	 * Getter for <code>core.master_data.address</code>.
	 */
	public java.lang.String getAddress() {
		return (java.lang.String) getValue(9);
	}

	/**
	 * Setter for <code>core.master_data.city</code>.
	 */
	public void setCity(java.lang.String value) {
		setValue(10, value);
	}

	/**
	 * Getter for <code>core.master_data.city</code>.
	 */
	public java.lang.String getCity() {
		return (java.lang.String) getValue(10);
	}

	/**
	 * Setter for <code>core.master_data.postal_code</code>.
	 */
	public void setPostalCode(java.lang.String value) {
		setValue(11, value);
	}

	/**
	 * Getter for <code>core.master_data.postal_code</code>.
	 */
	public java.lang.String getPostalCode() {
		return (java.lang.String) getValue(11);
	}

	/**
	 * Setter for <code>core.master_data.state</code>.
	 */
	public void setState(java.lang.String value) {
		setValue(12, value);
	}

	/**
	 * Getter for <code>core.master_data.state</code>.
	 */
	public java.lang.String getState() {
		return (java.lang.String) getValue(12);
	}

	/**
	 * Setter for <code>core.master_data.country</code>.
	 */
	public void setCountry(java.lang.String value) {
		setValue(13, value);
	}

	/**
	 * Getter for <code>core.master_data.country</code>.
	 */
	public java.lang.String getCountry() {
		return (java.lang.String) getValue(13);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Record2<java.lang.String, java.lang.String> key() {
		return (org.jooq.Record2) super.key();
	}

	// -------------------------------------------------------------------------
	// Record14 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row14<java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, org.joda.time.DateTime, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String> fieldsRow() {
		return (org.jooq.Row14) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row14<java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, org.joda.time.DateTime, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String> valuesRow() {
		return (org.jooq.Row14) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field1() {
		return com.sonicle.webtop.core.jooq.core.tables.MasterData.MASTER_DATA.DOMAIN_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field2() {
		return com.sonicle.webtop.core.jooq.core.tables.MasterData.MASTER_DATA.MASTER_DATA_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field3() {
		return com.sonicle.webtop.core.jooq.core.tables.MasterData.MASTER_DATA.PARENT_MASTER_DATA_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field4() {
		return com.sonicle.webtop.core.jooq.core.tables.MasterData.MASTER_DATA.EXTERNAL_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field5() {
		return com.sonicle.webtop.core.jooq.core.tables.MasterData.MASTER_DATA.TYPE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field6() {
		return com.sonicle.webtop.core.jooq.core.tables.MasterData.MASTER_DATA.REVISION_STATUS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<org.joda.time.DateTime> field7() {
		return com.sonicle.webtop.core.jooq.core.tables.MasterData.MASTER_DATA.REVISION_TIMESTAMP;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field8() {
		return com.sonicle.webtop.core.jooq.core.tables.MasterData.MASTER_DATA.REVISION_SEQUENCE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field9() {
		return com.sonicle.webtop.core.jooq.core.tables.MasterData.MASTER_DATA.DESCRIPTION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field10() {
		return com.sonicle.webtop.core.jooq.core.tables.MasterData.MASTER_DATA.ADDRESS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field11() {
		return com.sonicle.webtop.core.jooq.core.tables.MasterData.MASTER_DATA.CITY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field12() {
		return com.sonicle.webtop.core.jooq.core.tables.MasterData.MASTER_DATA.POSTAL_CODE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field13() {
		return com.sonicle.webtop.core.jooq.core.tables.MasterData.MASTER_DATA.STATE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field14() {
		return com.sonicle.webtop.core.jooq.core.tables.MasterData.MASTER_DATA.COUNTRY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value1() {
		return getDomainId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value2() {
		return getMasterDataId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value3() {
		return getParentMasterDataId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value4() {
		return getExternalId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value5() {
		return getType();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value6() {
		return getRevisionStatus();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.joda.time.DateTime value7() {
		return getRevisionTimestamp();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value8() {
		return getRevisionSequence();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value9() {
		return getDescription();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value10() {
		return getAddress();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value11() {
		return getCity();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value12() {
		return getPostalCode();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value13() {
		return getState();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value14() {
		return getCountry();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MasterDataRecord value1(java.lang.String value) {
		setDomainId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MasterDataRecord value2(java.lang.String value) {
		setMasterDataId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MasterDataRecord value3(java.lang.String value) {
		setParentMasterDataId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MasterDataRecord value4(java.lang.String value) {
		setExternalId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MasterDataRecord value5(java.lang.String value) {
		setType(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MasterDataRecord value6(java.lang.String value) {
		setRevisionStatus(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MasterDataRecord value7(org.joda.time.DateTime value) {
		setRevisionTimestamp(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MasterDataRecord value8(java.lang.Integer value) {
		setRevisionSequence(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MasterDataRecord value9(java.lang.String value) {
		setDescription(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MasterDataRecord value10(java.lang.String value) {
		setAddress(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MasterDataRecord value11(java.lang.String value) {
		setCity(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MasterDataRecord value12(java.lang.String value) {
		setPostalCode(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MasterDataRecord value13(java.lang.String value) {
		setState(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MasterDataRecord value14(java.lang.String value) {
		setCountry(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MasterDataRecord values(java.lang.String value1, java.lang.String value2, java.lang.String value3, java.lang.String value4, java.lang.String value5, java.lang.String value6, org.joda.time.DateTime value7, java.lang.Integer value8, java.lang.String value9, java.lang.String value10, java.lang.String value11, java.lang.String value12, java.lang.String value13, java.lang.String value14) {
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached MasterDataRecord
	 */
	public MasterDataRecord() {
		super(com.sonicle.webtop.core.jooq.core.tables.MasterData.MASTER_DATA);
	}

	/**
	 * Create a detached, initialised MasterDataRecord
	 */
	public MasterDataRecord(java.lang.String domainId, java.lang.String masterDataId, java.lang.String parentMasterDataId, java.lang.String externalId, java.lang.String type, java.lang.String revisionStatus, org.joda.time.DateTime revisionTimestamp, java.lang.Integer revisionSequence, java.lang.String description, java.lang.String address, java.lang.String city, java.lang.String postalCode, java.lang.String state, java.lang.String country) {
		super(com.sonicle.webtop.core.jooq.core.tables.MasterData.MASTER_DATA);

		setValue(0, domainId);
		setValue(1, masterDataId);
		setValue(2, parentMasterDataId);
		setValue(3, externalId);
		setValue(4, type);
		setValue(5, revisionStatus);
		setValue(6, revisionTimestamp);
		setValue(7, revisionSequence);
		setValue(8, description);
		setValue(9, address);
		setValue(10, city);
		setValue(11, postalCode);
		setValue(12, state);
		setValue(13, country);
	}
}