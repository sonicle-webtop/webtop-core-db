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
public class Domains implements java.io.Serializable {

	private static final long serialVersionUID = 293361251;

	private java.lang.String  domainId;
	private java.lang.String  internetName;
	private java.lang.Boolean enabled;
	private java.lang.String  description;
	private java.lang.Boolean userAutoCreation;
	private java.lang.String  dirUri;
	private java.lang.String  dirAdmin;
	private java.lang.String  dirPassword;
	private java.lang.String  dirConnectionSecurity;
	private java.lang.Boolean dirCaseSensitive;
	private java.lang.Boolean dirPasswordPolicy;
	private java.lang.String  dirParameters;

	public Domains() {}

	public Domains(
		java.lang.String  domainId,
		java.lang.String  internetName,
		java.lang.Boolean enabled,
		java.lang.String  description,
		java.lang.Boolean userAutoCreation,
		java.lang.String  dirUri,
		java.lang.String  dirAdmin,
		java.lang.String  dirPassword,
		java.lang.String  dirConnectionSecurity,
		java.lang.Boolean dirCaseSensitive,
		java.lang.Boolean dirPasswordPolicy,
		java.lang.String  dirParameters
	) {
		this.domainId = domainId;
		this.internetName = internetName;
		this.enabled = enabled;
		this.description = description;
		this.userAutoCreation = userAutoCreation;
		this.dirUri = dirUri;
		this.dirAdmin = dirAdmin;
		this.dirPassword = dirPassword;
		this.dirConnectionSecurity = dirConnectionSecurity;
		this.dirCaseSensitive = dirCaseSensitive;
		this.dirPasswordPolicy = dirPasswordPolicy;
		this.dirParameters = dirParameters;
	}

	public java.lang.String getDomainId() {
		return this.domainId;
	}

	public void setDomainId(java.lang.String domainId) {
		this.domainId = domainId;
	}

	public java.lang.String getInternetName() {
		return this.internetName;
	}

	public void setInternetName(java.lang.String internetName) {
		this.internetName = internetName;
	}

	public java.lang.Boolean getEnabled() {
		return this.enabled;
	}

	public void setEnabled(java.lang.Boolean enabled) {
		this.enabled = enabled;
	}

	public java.lang.String getDescription() {
		return this.description;
	}

	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	public java.lang.Boolean getUserAutoCreation() {
		return this.userAutoCreation;
	}

	public void setUserAutoCreation(java.lang.Boolean userAutoCreation) {
		this.userAutoCreation = userAutoCreation;
	}

	public java.lang.String getDirUri() {
		return this.dirUri;
	}

	public void setDirUri(java.lang.String dirUri) {
		this.dirUri = dirUri;
	}

	public java.lang.String getDirAdmin() {
		return this.dirAdmin;
	}

	public void setDirAdmin(java.lang.String dirAdmin) {
		this.dirAdmin = dirAdmin;
	}

	public java.lang.String getDirPassword() {
		return this.dirPassword;
	}

	public void setDirPassword(java.lang.String dirPassword) {
		this.dirPassword = dirPassword;
	}

	public java.lang.String getDirConnectionSecurity() {
		return this.dirConnectionSecurity;
	}

	public void setDirConnectionSecurity(java.lang.String dirConnectionSecurity) {
		this.dirConnectionSecurity = dirConnectionSecurity;
	}

	public java.lang.Boolean getDirCaseSensitive() {
		return this.dirCaseSensitive;
	}

	public void setDirCaseSensitive(java.lang.Boolean dirCaseSensitive) {
		this.dirCaseSensitive = dirCaseSensitive;
	}

	public java.lang.Boolean getDirPasswordPolicy() {
		return this.dirPasswordPolicy;
	}

	public void setDirPasswordPolicy(java.lang.Boolean dirPasswordPolicy) {
		this.dirPasswordPolicy = dirPasswordPolicy;
	}

	public java.lang.String getDirParameters() {
		return this.dirParameters;
	}

	public void setDirParameters(java.lang.String dirParameters) {
		this.dirParameters = dirParameters;
	}
}