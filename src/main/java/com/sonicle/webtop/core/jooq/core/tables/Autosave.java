/**
 * This class is generated by jOOQ
 */
package com.sonicle.webtop.core.jooq.core.tables;

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
public class Autosave extends org.jooq.impl.TableImpl<com.sonicle.webtop.core.jooq.core.tables.records.AutosaveRecord> {

	private static final long serialVersionUID = -406360588;

	/**
	 * The reference instance of <code>core.autosave</code>
	 */
	public static final com.sonicle.webtop.core.jooq.core.tables.Autosave AUTOSAVE = new com.sonicle.webtop.core.jooq.core.tables.Autosave();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<com.sonicle.webtop.core.jooq.core.tables.records.AutosaveRecord> getRecordType() {
		return com.sonicle.webtop.core.jooq.core.tables.records.AutosaveRecord.class;
	}

	/**
	 * The column <code>core.autosave.domain_id</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.core.jooq.core.tables.records.AutosaveRecord, java.lang.String> DOMAIN_ID = createField("domain_id", org.jooq.impl.SQLDataType.VARCHAR.length(20).nullable(false), this, "");

	/**
	 * The column <code>core.autosave.user_id</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.core.jooq.core.tables.records.AutosaveRecord, java.lang.String> USER_ID = createField("user_id", org.jooq.impl.SQLDataType.VARCHAR.length(100).nullable(false), this, "");

	/**
	 * The column <code>core.autosave.webtop_client_id</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.core.jooq.core.tables.records.AutosaveRecord, java.lang.String> WEBTOP_CLIENT_ID = createField("webtop_client_id", org.jooq.impl.SQLDataType.CHAR.length(36).nullable(false), this, "");

	/**
	 * The column <code>core.autosave.service_id</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.core.jooq.core.tables.records.AutosaveRecord, java.lang.String> SERVICE_ID = createField("service_id", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

	/**
	 * The column <code>core.autosave.context</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.core.jooq.core.tables.records.AutosaveRecord, java.lang.String> CONTEXT = createField("context", org.jooq.impl.SQLDataType.VARCHAR.length(50).nullable(false), this, "");

	/**
	 * The column <code>core.autosave.key</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.core.jooq.core.tables.records.AutosaveRecord, java.lang.String> KEY = createField("key", org.jooq.impl.SQLDataType.VARCHAR.length(100).nullable(false), this, "");

	/**
	 * The column <code>core.autosave.value</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.core.jooq.core.tables.records.AutosaveRecord, java.lang.String> VALUE = createField("value", org.jooq.impl.SQLDataType.CLOB, this, "");

	/**
	 * Create a <code>core.autosave</code> table reference
	 */
	public Autosave() {
		this("autosave", null);
	}

	/**
	 * Create an aliased <code>core.autosave</code> table reference
	 */
	public Autosave(java.lang.String alias) {
		this(alias, com.sonicle.webtop.core.jooq.core.tables.Autosave.AUTOSAVE);
	}

	private Autosave(java.lang.String alias, org.jooq.Table<com.sonicle.webtop.core.jooq.core.tables.records.AutosaveRecord> aliased) {
		this(alias, aliased, null);
	}

	private Autosave(java.lang.String alias, org.jooq.Table<com.sonicle.webtop.core.jooq.core.tables.records.AutosaveRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, com.sonicle.webtop.core.jooq.core.Core.CORE, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<com.sonicle.webtop.core.jooq.core.tables.records.AutosaveRecord> getPrimaryKey() {
		return com.sonicle.webtop.core.jooq.core.Keys.AUTOSAVE_PKEY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<com.sonicle.webtop.core.jooq.core.tables.records.AutosaveRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<com.sonicle.webtop.core.jooq.core.tables.records.AutosaveRecord>>asList(com.sonicle.webtop.core.jooq.core.Keys.AUTOSAVE_PKEY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public com.sonicle.webtop.core.jooq.core.tables.Autosave as(java.lang.String alias) {
		return new com.sonicle.webtop.core.jooq.core.tables.Autosave(alias, this);
	}

	/**
	 * Rename this table
	 */
	public com.sonicle.webtop.core.jooq.core.tables.Autosave rename(java.lang.String name) {
		return new com.sonicle.webtop.core.jooq.core.tables.Autosave(name, null);
	}
}
