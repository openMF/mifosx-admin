package org.mifosx.admin.domain;

import java.util.List;

public interface IBaseDAO {

	/**
	 * Gets List of all Tenants present in the MifosX installation
	 * 
	 * @return
	 */
	public List<Tenant> retrieveTenants();

	/**
	 * Adds key statistics like number of active clients, loans, groups, center
	 * etc to the list of tenants
	 * 
	 * @param tenants
	 */
	public void appendTenantStatistics(List<Tenant> tenants);

	/**
	 * Creates an Entry in `mifosplatform-tenants`.tenants for a new Tenant, also
	 * initializes an empty database for the tenant
	 * 
	 * @param tenant
	 * @return
	 */
	public Tenant createTenant(Tenant tenant);

	/**
	 * Removes entry for this Tenant from `mifosplatform-tenants`.tenants. Does
	 * not delete the database associated with the Tenant
	 * 
	 * @param id
	 */
	public void deleteTenant(Integer id);

	/**
	 * Retrieve tenant with a given Id
	 * 
	 * @param id
	 * @return
	 */
	Tenant retrieveTenant(Integer id);

}
