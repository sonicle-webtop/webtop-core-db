/**
 * This class is generated by jOOQ
 */
package com.sonicle.webtop.core.jooq.core.tables.pojos;

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
public class FileTypes implements java.io.Serializable {

	private static final long serialVersionUID = -723474397;

	private java.lang.String extension;
	private java.lang.String type;
	private java.lang.String subtype;

	public FileTypes() {}

	public FileTypes(
		java.lang.String extension,
		java.lang.String type,
		java.lang.String subtype
	) {
		this.extension = extension;
		this.type = type;
		this.subtype = subtype;
	}

	public java.lang.String getExtension() {
		return this.extension;
	}

	public void setExtension(java.lang.String extension) {
		this.extension = extension;
	}

	public java.lang.String getType() {
		return this.type;
	}

	public void setType(java.lang.String type) {
		this.type = type;
	}

	public java.lang.String getSubtype() {
		return this.subtype;
	}

	public void setSubtype(java.lang.String subtype) {
		this.subtype = subtype;
	}
}