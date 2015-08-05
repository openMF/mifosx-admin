package org.mifosx.admin.api;

import java.util.List;

import org.mifosx.admin.domain.Tenant;
import org.mifosx.admin.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TenantsApiResource {

	@Autowired
	IBaseService baseService;

	@RequestMapping("/tenants")
	public @ResponseBody List<Tenant> greeting() {
		System.out.println("==== in tenantsApiResource ====");
		return baseService.fetchTenantDetails();
	}
}
