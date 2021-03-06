/**
 * This class is generated by jOOQ
 */
package com.sonicle.webtop.core.jooq.config.tables.records;

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
public class VirtualDomainNamesRecord extends org.jooq.impl.UpdatableRecordImpl<com.sonicle.webtop.core.jooq.config.tables.records.VirtualDomainNamesRecord> implements org.jooq.Record4<java.lang.Integer, java.lang.String, java.lang.String, java.lang.String> {

	private static final long serialVersionUID = 1207961017;

	/**
	 * Setter for <code>config.virtual_domain_names.virtual_domain_name_id</code>.
	 */
	public void setVirtualDomainNameId(java.lang.Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>config.virtual_domain_names.virtual_domain_name_id</code>.
	 */
	public java.lang.Integer getVirtualDomainNameId() {
		return (java.lang.Integer) getValue(0);
	}

	/**
	 * Setter for <code>config.virtual_domain_names.context</code>.
	 */
	public void setContext(java.lang.String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>config.virtual_domain_names.context</code>.
	 */
	public java.lang.String getContext() {
		return (java.lang.String) getValue(1);
	}

	/**
	 * Setter for <code>config.virtual_domain_names.primary_name</code>.
	 */
	public void setPrimaryName(java.lang.String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>config.virtual_domain_names.primary_name</code>.
	 */
	public java.lang.String getPrimaryName() {
		return (java.lang.String) getValue(2);
	}

	/**
	 * Setter for <code>config.virtual_domain_names.virtual_name</code>.
	 */
	public void setVirtualName(java.lang.String value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>config.virtual_domain_names.virtual_name</code>.
	 */
	public java.lang.String getVirtualName() {
		return (java.lang.String) getValue(3);
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
	// Record4 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row4<java.lang.Integer, java.lang.String, java.lang.String, java.lang.String> fieldsRow() {
		return (org.jooq.Row4) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row4<java.lang.Integer, java.lang.String, java.lang.String, java.lang.String> valuesRow() {
		return (org.jooq.Row4) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field1() {
		return com.sonicle.webtop.core.jooq.config.tables.VirtualDomainNames.VIRTUAL_DOMAIN_NAMES.VIRTUAL_DOMAIN_NAME_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field2() {
		return com.sonicle.webtop.core.jooq.config.tables.VirtualDomainNames.VIRTUAL_DOMAIN_NAMES.CONTEXT;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field3() {
		return com.sonicle.webtop.core.jooq.config.tables.VirtualDomainNames.VIRTUAL_DOMAIN_NAMES.PRIMARY_NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field4() {
		return com.sonicle.webtop.core.jooq.config.tables.VirtualDomainNames.VIRTUAL_DOMAIN_NAMES.VIRTUAL_NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value1() {
		return getVirtualDomainNameId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value2() {
		return getContext();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value3() {
		return getPrimaryName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value4() {
		return getVirtualName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VirtualDomainNamesRecord value1(java.lang.Integer value) {
		setVirtualDomainNameId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VirtualDomainNamesRecord value2(java.lang.String value) {
		setContext(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VirtualDomainNamesRecord value3(java.lang.String value) {
		setPrimaryName(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VirtualDomainNamesRecord value4(java.lang.String value) {
		setVirtualName(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VirtualDomainNamesRecord values(java.lang.Integer value1, java.lang.String value2, java.lang.String value3, java.lang.String value4) {
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached VirtualDomainNamesRecord
	 */
	public VirtualDomainNamesRecord() {
		super(com.sonicle.webtop.core.jooq.config.tables.VirtualDomainNames.VIRTUAL_DOMAIN_NAMES);
	}

	/**
	 * Create a detached, initialised VirtualDomainNamesRecord
	 */
	public VirtualDomainNamesRecord(java.lang.Integer virtualDomainNameId, java.lang.String context, java.lang.String primaryName, java.lang.String virtualName) {
		super(com.sonicle.webtop.core.jooq.config.tables.VirtualDomainNames.VIRTUAL_DOMAIN_NAMES);

		setValue(0, virtualDomainNameId);
		setValue(1, context);
		setValue(2, primaryName);
		setValue(3, virtualName);
	}
}
