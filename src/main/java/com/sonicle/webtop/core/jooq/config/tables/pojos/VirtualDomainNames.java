/**
 * This class is generated by jOOQ
 */
package com.sonicle.webtop.core.jooq.config.tables.pojos;

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
public class VirtualDomainNames implements java.io.Serializable {

	private static final long serialVersionUID = -277512430;

	private java.lang.Integer virtualDomainNameId;
	private java.lang.String  context;
	private java.lang.String  primaryName;
	private java.lang.String  virtualName;

	public VirtualDomainNames() {}

	public VirtualDomainNames(
		java.lang.Integer virtualDomainNameId,
		java.lang.String  context,
		java.lang.String  primaryName,
		java.lang.String  virtualName
	) {
		this.virtualDomainNameId = virtualDomainNameId;
		this.context = context;
		this.primaryName = primaryName;
		this.virtualName = virtualName;
	}

	public java.lang.Integer getVirtualDomainNameId() {
		return this.virtualDomainNameId;
	}

	public void setVirtualDomainNameId(java.lang.Integer virtualDomainNameId) {
		this.virtualDomainNameId = virtualDomainNameId;
	}

	public java.lang.String getContext() {
		return this.context;
	}

	public void setContext(java.lang.String context) {
		this.context = context;
	}

	public java.lang.String getPrimaryName() {
		return this.primaryName;
	}

	public void setPrimaryName(java.lang.String primaryName) {
		this.primaryName = primaryName;
	}

	public java.lang.String getVirtualName() {
		return this.virtualName;
	}

	public void setVirtualName(java.lang.String virtualName) {
		this.virtualName = virtualName;
	}
}