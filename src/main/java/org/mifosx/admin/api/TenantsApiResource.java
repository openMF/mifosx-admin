package org.mifosx.admin.api;

import java.util.List;

import org.mifosx.admin.domain.Tenant;
import org.mifosx.admin.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/tenants")
public class TenantsApiResource {

	@Autowired
	IBaseService baseService;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<Tenant> getAllTenants() {
		return baseService.getTenantDetails();
	}

	@RequestMapping(method = RequestMethod.POST)
	public Tenant create(@RequestBody Tenant tenant) {
		return baseService.createTenant(tenant);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "{id}")
	public void delete(@PathVariable Integer id) {
		baseService.deleteTenant(id);
	}

}
