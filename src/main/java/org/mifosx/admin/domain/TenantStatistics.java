package org.mifosx.admin.domain;

import java.util.Date;

public class TenantStatistics {

	private final Date lastUpdateDate;

	private final Integer activeCenters;

	private final Integer activeGroups;

	private final Integer activeClients;

	private final Integer activeLoanAccounts;

	private final Integer activeSavingsAccounts;

	public Date getLastUpdateDate() {
		return this.lastUpdateDate;
	}

	public Integer getActiveCenters() {
		return this.activeCenters;
	}

	public Integer getActiveGroups() {
		return this.activeGroups;
	}

	public Integer getActiveClients() {
		return this.activeClients;
	}

	public Integer getActiveLoanAccounts() {
		return this.activeLoanAccounts;
	}

	public Integer getActiveSavingsAccounts() {
		return this.activeSavingsAccounts;
	}

	public TenantStatistics(Date lastUpdateDate, Integer activeCenters,
			Integer activeGroups, Integer activeClients,
			Integer activeLoanAccounts, Integer activeSavingsAccounts) {
		super();
		this.lastUpdateDate = lastUpdateDate;
		this.activeCenters = activeCenters;
		this.activeGroups = activeGroups;
		this.activeClients = activeClients;
		this.activeLoanAccounts = activeLoanAccounts;
		this.activeSavingsAccounts = activeSavingsAccounts;
	}

}
