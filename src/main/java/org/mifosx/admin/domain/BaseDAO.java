package org.mifosx.admin.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class BaseDAO implements IBaseDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;

	private static final class TenantMapper implements RowMapper<Tenant> {

		@Override
		public Tenant mapRow(ResultSet rs,
				@SuppressWarnings("unused") int rowNum) throws SQLException {
			Integer id = rs.getInt("id");
			String name = rs.getString("name");
			String timeZone = rs.getString("timezone_id");
			String identifier = rs.getString("identifier");
			String schemaName = rs.getString("schema_name");
			return new Tenant(id, identifier, name, timeZone, schemaName);
		}
	}

	@Override
	public List<Tenant> retrieveTenants() {
		String fetchTenantsSQL = "select id, name, identifier, timezone_id, schema_name from tenants";
		List<Tenant> tenants = jdbcTemplate.query(fetchTenantsSQL,
				new TenantMapper());
		return tenants;
	}

	@Override
	public Tenant retrieveTenant(Integer id) {
		String fetchTenantsSQL = "select t.id, name, identifier, timezone_id, t.schema_name from tenants,tenant_server_connections t where tenants.id = ? and tenants.id=t.id";
		Tenant tenant = jdbcTemplate.queryForObject(fetchTenantsSQL,
				new Object[] { id }, new TenantMapper());
		return tenant;
	}

	@Override
	public void appendTenantStatistics(List<Tenant> tenants) {

		StringBuilder lastLoginDateQuery = new StringBuilder();

		StringBuilder activeCentersQuery = new StringBuilder();

		StringBuilder activeGroupsQuery = new StringBuilder();

		StringBuilder activeClientsQuery = new StringBuilder();

		StringBuilder activeLoanAccountsQuery = new StringBuilder();

		StringBuilder activeSavingsAccountsQuery = new StringBuilder();

		// generate queries for statistics across tenants
		for (Tenant tenant : tenants) {
			lastLoginDateQuery.append(" union select max(made_on_date) from `"
					+ tenant.getSchemaName() + "`.m_portfolio_command_source");

			activeCentersQuery
					.append(" union select count(*) from `"
							+ tenant.getSchemaName()
							+ "`.m_group where status_enum >=300 and status_enum < 600 and level_id=1 ");

			activeGroupsQuery
					.append(" union select count(*) from `"
							+ tenant.getSchemaName()
							+ "`.m_group where status_enum >=300 and status_enum < 600 and level_id=2 ");

			activeClientsQuery
					.append(" union select count(*) from `"
							+ tenant.getSchemaName()
							+ "`.m_client where status_enum >=300 and status_enum < 600 ");

			activeLoanAccountsQuery
					.append(" union select count(*) from `"
							+ tenant.getSchemaName()
							+ "`.m_loan  where (loan_status_id > 200 and loan_status_id < 400) or loan_status_id = 700 ");

			activeSavingsAccountsQuery
					.append(" union select count(*) from `"
							+ tenant.getSchemaName()
							+ "`.m_savings_account  where (status_enum > 200 and status_enum < 400) or status_enum = 800 ");

		}

		// Run SQL queries for generating statistics
		String lastLoginDateQueryAsString = convertToValidSQLQuery(lastLoginDateQuery);
		String activeCentersQueryAsString = convertToValidSQLQuery(activeCentersQuery);
		String activeGroupsQueryAsString = convertToValidSQLQuery(activeGroupsQuery);
		String activeClientsQueryAsString = convertToValidSQLQuery(activeClientsQuery);
		String activeLoanAccountsQueryAsString = convertToValidSQLQuery(activeLoanAccountsQuery);
		String activeSavingsAccountsQueryAsString = convertToValidSQLQuery(activeSavingsAccountsQuery);

		List<Date> lastLoginDates = jdbcTemplate.queryForList(
				lastLoginDateQueryAsString, Date.class);
		List<Integer> activeCenters = jdbcTemplate.queryForList(
				activeCentersQueryAsString, Integer.class);
		List<Integer> activeGroups = jdbcTemplate.queryForList(
				activeGroupsQueryAsString, Integer.class);
		List<Integer> activeClients = jdbcTemplate.queryForList(
				activeClientsQueryAsString, Integer.class);
		List<Integer> activeLoanAccounts = jdbcTemplate.queryForList(
				activeLoanAccountsQueryAsString, Integer.class);
		List<Integer> activeSavingsAccounts = jdbcTemplate.queryForList(
				activeSavingsAccountsQueryAsString, Integer.class);

		// update statistics back in Tenants
		for (int i = 0; i < tenants.size(); i++) {
			tenants.get(i).setStatistics(
					new TenantStatistics(lastLoginDates.get(i), activeCenters
							.get(i), activeGroups.get(i), activeClients.get(i),
							activeLoanAccounts.get(i), activeSavingsAccounts
									.get(i)));
		}
	}

	/**
	 * @param lastLoginDateQuery
	 * @return
	 */
	private String convertToValidSQLQuery(StringBuilder lastLoginDateQuery) {
		return lastLoginDateQuery.toString().replaceFirst("union", "");
	}

	@Override
	public void deleteTenant(Integer id) {
		this.jdbcTemplate.update("delete from tenants,tenant_server_connections t where tenants.id = ? and tenants.id=t.id", id);
	}

	@Override
	public Tenant createTenant(Tenant tenant) {
		// create an entry in tenants Table
		this.jdbcTemplate
				.update("INSERT INTO `tenants` (`identifier`, `name`, `schema_name`, `timezone_id`) VALUES (?, ?, ?, ?)",
						tenant.getIdentifier(), tenant.getName(),
						tenant.getSchemaName());
		// create a database
		this.jdbcTemplate.execute("create database " + tenant.getSchemaName());

		return tenant;
	}
}
