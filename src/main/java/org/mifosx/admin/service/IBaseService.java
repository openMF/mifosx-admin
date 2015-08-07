package org.mifosx.admin.service;

import java.util.List;

import org.mifosx.admin.domain.Tenant;

public interface IBaseService {

	public List<Tenant> getTenantDetails();

	public Tenant createTenant(Tenant tenant);

	public Tenant deleteTenant(int id);

}
