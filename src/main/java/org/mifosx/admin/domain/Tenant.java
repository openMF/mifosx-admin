package org.mifosx.admin.domain;


public class Tenant {

	private final Integer id;

	private final String identifier;

	private final String name;

	private final String timeZone;

	private final String schemaName;

	private TenantStatistics statistics;

	public Tenant(Integer id, String identifier, String name, String timeZone,
			String schemaName) {
		super();
		this.id = id;
		this.identifier = identifier;
		this.timeZone = timeZone;
		this.name = name;
		this.schemaName = schemaName;
	}

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

	public TenantStatistics getStatistics() {
		return this.statistics;
	}

	public void setStatistics(TenantStatistics statistics) {
		this.statistics = statistics;
	}

	public String getTimeZone() {
		return this.timeZone;
	}

}
