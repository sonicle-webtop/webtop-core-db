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
public class RolesPermissions implements java.io.Serializable {

	private static final long serialVersionUID = -251086693;

	private java.lang.Integer rolePermissionId;
	private java.lang.String  roleUid;
	private java.lang.String  serviceId;
	private java.lang.String  key;
	private java.lang.String  action;
	private java.lang.String  instance;

	public RolesPermissions() {}

	public RolesPermissions(
		java.lang.Integer rolePermissionId,
		java.lang.String  roleUid,
		java.lang.String  serviceId,
		java.lang.String  key,
		java.lang.String  action,
		java.lang.String  instance
	) {
		this.rolePermissionId = rolePermissionId;
		this.roleUid = roleUid;
		this.serviceId = serviceId;
		this.key = key;
		this.action = action;
		this.instance = instance;
	}

	public java.lang.Integer getRolePermissionId() {
		return this.rolePermissionId;
	}

	public void setRolePermissionId(java.lang.Integer rolePermissionId) {
		this.rolePermissionId = rolePermissionId;
	}

	public java.lang.String getRoleUid() {
		return this.roleUid;
	}

	public void setRoleUid(java.lang.String roleUid) {
		this.roleUid = roleUid;
	}

	public java.lang.String getServiceId() {
		return this.serviceId;
	}

	public void setServiceId(java.lang.String serviceId) {
		this.serviceId = serviceId;
	}

	public java.lang.String getKey() {
		return this.key;
	}

	public void setKey(java.lang.String key) {
		this.key = key;
	}

	public java.lang.String getAction() {
		return this.action;
	}

	public void setAction(java.lang.String action) {
		this.action = action;
	}

	public java.lang.String getInstance() {
		return this.instance;
	}

	public void setInstance(java.lang.String instance) {
		this.instance = instance;
	}
}