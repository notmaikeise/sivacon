package br.com.sivacon.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class VehicleCatalogDao {

    private final NamedParameterJdbcTemplate jdbc;

    public VehicleCatalogDao(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<VehicleRow> findPublishedAvailable(String brand, BigDecimal maxPrice) {
        var sql = new StringBuilder("""
                SELECT u.id, m.brand, m.name, u.price, u.condition, u.status, u.description
                FROM vehicle_unit u
                JOIN vehicle_model m ON m.id = u.model_id
                WHERE u.published = true AND u.status = 'AVAILABLE'
                """);
        var params = new MapSqlParameterSource();
        if (brand != null && !brand.isBlank()) {
            sql.append(" AND lower(m.brand) = lower(:brand)");
            params.addValue("brand", brand.trim());
        }
        if (maxPrice != null) {
            sql.append(" AND u.price <= :maxPrice");
            params.addValue("maxPrice", maxPrice);
        }
        sql.append(" ORDER BY m.brand, m.name, u.price");
        return jdbc.query(sql.toString(), params, (rs, rowNum) -> mapRow(rs));
    }

    public VehicleRow findPublishedById(UUID id) {
        return jdbc.query("""
                        SELECT u.id, m.brand, m.name, u.price, u.condition, u.status, u.description
                        FROM vehicle_unit u
                        JOIN vehicle_model m ON m.id = u.model_id
                        WHERE u.id = :id AND u.published = true
                        """,
                new MapSqlParameterSource("id", id),
                (rs, rowNum) -> mapRow(rs))
                .stream().findFirst().orElse(null);
    }

    public boolean isPublishedAndAvailable(UUID vehicleId) {
        return Boolean.TRUE.equals(jdbc.query("""
                        SELECT EXISTS (
                            SELECT 1 FROM vehicle_unit
                            WHERE id = :vehicleId
                              AND published = true
                              AND status = 'AVAILABLE'
                        )
                        """,
                new MapSqlParameterSource("vehicleId", vehicleId),
                (rs, rowNum) -> rs.getBoolean(1))
                .stream().findFirst().orElse(false));
    }

    private VehicleRow mapRow(java.sql.ResultSet rs) throws java.sql.SQLException {
        return new VehicleRow(
                rs.getObject("id", UUID.class),
                rs.getString("brand"),
                rs.getString("name"),
                rs.getBigDecimal("price"),
                rs.getString("condition"),
                rs.getString("status"),
                rs.getString("description"));
    }

    public record VehicleRow(UUID id, String brand, String name, BigDecimal price,
                             String condition, String status, String description) {
    }
}
