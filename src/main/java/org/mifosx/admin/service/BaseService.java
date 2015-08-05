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
	public List<Tenant> fetchTenantDetails() {
		List<Tenant> tenants = dao.retrieveTenants();
		for (Tenant tenant : tenants) {
			dao.enrichTenantDetails(tenant);
		}
		return tenants;
	}

}
