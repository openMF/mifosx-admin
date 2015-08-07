package org.mifosx.admin.service;

import java.util.List;

import org.mifosx.admin.domain.IBaseDAO;
import org.mifosx.admin.domain.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseService implements IBaseService {

	@Autowired
	IBaseDAO dao;

	@Override
	public List<Tenant> getTenantDetails() {
		List<Tenant> tenants = dao.retrieveTenants();
		// also get key statistics associated with each Tenant
		dao.appendTenantStatistics(tenants);
		return tenants;
	}

	@Override
	public Tenant createTenant(Tenant tenant) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tenant deleteTenant(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
