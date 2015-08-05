package org.mifosx.admin.domain;

import java.util.List;

public interface IBaseDAO {
	public List<Tenant> retrieveTenants();

	public Tenant enrichTenantDetails(Tenant tenant);
}
