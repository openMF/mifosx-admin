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
			String identifier = rs.getString("identifier");
			String schemaName = rs.getString("schema_name");
			return new Tenant(id, identifier, name, schemaName);
		}
	}

	@Override
	public List<Tenant> retrieveTenants() {
		String fetchTenantsSQL = "select id, name, identifier, schema_name from tenants";
		List<Tenant> tenants = jdbcTemplate.query(fetchTenantsSQL,
				new TenantMapper());
		return tenants;
	}

	@Override
	public Tenant enrichTenantDetails(Tenant tenant) {
		Date lastLoginDate = jdbcTemplate.queryForObject(
				"select max(made_on_date) from `" + tenant.getSchemaName()
						+ "`.m_portfolio_command_source", Date.class);
		tenant.setLastLoginDate(lastLoginDate);
		return tenant;
	}

}
