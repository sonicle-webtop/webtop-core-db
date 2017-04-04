/**
 * This class is generated by jOOQ
 */
package com.sonicle.webtop.core.jooq.config.tables;

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
public class VirtualDomainNames extends org.jooq.impl.TableImpl<com.sonicle.webtop.core.jooq.config.tables.records.VirtualDomainNamesRecord> {

	private static final long serialVersionUID = -262693068;

	/**
	 * The reference instance of <code>config.virtual_domain_names</code>
	 */
	public static final com.sonicle.webtop.core.jooq.config.tables.VirtualDomainNames VIRTUAL_DOMAIN_NAMES = new com.sonicle.webtop.core.jooq.config.tables.VirtualDomainNames();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<com.sonicle.webtop.core.jooq.config.tables.records.VirtualDomainNamesRecord> getRecordType() {
		return com.sonicle.webtop.core.jooq.config.tables.records.VirtualDomainNamesRecord.class;
	}

	/**
	 * The column <code>config.virtual_domain_names.virtual_domain_name_id</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.core.jooq.config.tables.records.VirtualDomainNamesRecord, java.lang.Integer> VIRTUAL_DOMAIN_NAME_ID = createField("virtual_domain_name_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>config.virtual_domain_names.context</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.core.jooq.config.tables.records.VirtualDomainNamesRecord, java.lang.String> CONTEXT = createField("context", org.jooq.impl.SQLDataType.VARCHAR.length(100).nullable(false), this, "");

	/**
	 * The column <code>config.virtual_domain_names.primary_name</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.core.jooq.config.tables.records.VirtualDomainNamesRecord, java.lang.String> PRIMARY_NAME = createField("primary_name", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

	/**
	 * The column <code>config.virtual_domain_names.virtual_name</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.core.jooq.config.tables.records.VirtualDomainNamesRecord, java.lang.String> VIRTUAL_NAME = createField("virtual_name", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

	/**
	 * Create a <code>config.virtual_domain_names</code> table reference
	 */
	public VirtualDomainNames() {
		this("virtual_domain_names", null);
	}

	/**
	 * Create an aliased <code>config.virtual_domain_names</code> table reference
	 */
	public VirtualDomainNames(java.lang.String alias) {
		this(alias, com.sonicle.webtop.core.jooq.config.tables.VirtualDomainNames.VIRTUAL_DOMAIN_NAMES);
	}

	private VirtualDomainNames(java.lang.String alias, org.jooq.Table<com.sonicle.webtop.core.jooq.config.tables.records.VirtualDomainNamesRecord> aliased) {
		this(alias, aliased, null);
	}

	private VirtualDomainNames(java.lang.String alias, org.jooq.Table<com.sonicle.webtop.core.jooq.config.tables.records.VirtualDomainNamesRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, com.sonicle.webtop.core.jooq.config.Config.CONFIG, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Identity<com.sonicle.webtop.core.jooq.config.tables.records.VirtualDomainNamesRecord, java.lang.Integer> getIdentity() {
		return com.sonicle.webtop.core.jooq.config.Keys.IDENTITY_VIRTUAL_DOMAIN_NAMES;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<com.sonicle.webtop.core.jooq.config.tables.records.VirtualDomainNamesRecord> getPrimaryKey() {
		return com.sonicle.webtop.core.jooq.config.Keys.VIRTUAL_DOMAIN_NAMES_PKEY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<com.sonicle.webtop.core.jooq.config.tables.records.VirtualDomainNamesRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<com.sonicle.webtop.core.jooq.config.tables.records.VirtualDomainNamesRecord>>asList(com.sonicle.webtop.core.jooq.config.Keys.VIRTUAL_DOMAIN_NAMES_PKEY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public com.sonicle.webtop.core.jooq.config.tables.VirtualDomainNames as(java.lang.String alias) {
		return new com.sonicle.webtop.core.jooq.config.tables.VirtualDomainNames(alias, this);
	}

	/**
	 * Rename this table
	 */
	public com.sonicle.webtop.core.jooq.config.tables.VirtualDomainNames rename(java.lang.String name) {
		return new com.sonicle.webtop.core.jooq.config.tables.VirtualDomainNames(name, null);
	}
}