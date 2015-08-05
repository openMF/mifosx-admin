package org.mifosx.admin.domain;

import java.util.Date;

public class Tenant {

	private final Integer id;

	private final String identifier;

	private final String name;

	private final String schemaName;

	private Date lastLoginDate;

	public Integer getId() {
		return this.id;
	}

	public String getIdentifier() {
		return this.identifier;
	}

	public String getName() {
		return this.name;
	}

	public String getSchemaName() {
		return this.schemaName;
	}

	public Date getLastLoginDate() {
		return this.lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public Tenant(Integer id, String identifier, String name, String schemaName) {
		super();
		this.id = id;
		this.identifier = identifier;
		this.name = name;
		this.schemaName = schemaName;
	}

}
