package br.com.sivacon.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class InventoryDao {
    private final NamedParameterJdbcTemplate jdbc;

    public InventoryDao(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<InventoryRow> findAll() {
        return jdbc.query("""
                SELECT u.id, m.id model_id, m.brand, m.name, u.chassis, u.price,
                       u.condition, u.status, u.published, u.description
                FROM vehicle_unit u JOIN vehicle_model m ON m.id = u.model_id
                ORDER BY u.created_at DESC
                """, new MapSqlParameterSource(), (rs, n) -> new InventoryRow(
                rs.getObject("id", UUID.class), rs.getObject("model_id", UUID.class),
                rs.getString("brand"), rs.getString("name"), rs.getString("chassis"),
                rs.getBigDecimal("price"), rs.getString("condition"), rs.getString("status"),
                rs.getBoolean("published"), rs.getString("description")));
    }

    public UUID findOrCreateModel(String brand, String name, String engine, String category) {
        var existing = jdbc.query("""
                SELECT id FROM vehicle_model WHERE lower(brand) = lower(:brand)
                AND lower(name) = lower(:name)
                """, new MapSqlParameterSource().addValue("brand", brand).addValue("name", name),
                (rs, n) -> rs.getObject("id", UUID.class)).stream().findFirst();
        if (existing.isPresent()) return existing.get();
        var id = UUID.randomUUID();
        jdbc.update("""
                INSERT INTO vehicle_model (id, brand, name, engine_description, category)
                VALUES (:id, :brand, :name, :engine, :category)
                """, new MapSqlParameterSource().addValue("id", id).addValue("brand", brand)
                .addValue("name", name).addValue("engine", engine).addValue("category", category));
        return id;
    }

    public UUID insertUnit(UUID modelId, String chassis, BigDecimal price, String condition,
                           String description) {
        var id = UUID.randomUUID();
        jdbc.update("""
                INSERT INTO vehicle_unit
                    (id, model_id, chassis, price, condition, status, published, description)
                VALUES (:id, :modelId, :chassis, :price, :condition, 'DRAFT', false, :description)
                """, new MapSqlParameterSource().addValue("id", id).addValue("modelId", modelId)
                .addValue("chassis", chassis).addValue("price", price)
                .addValue("condition", condition).addValue("description", description));
        return id;
    }

    public boolean setPublished(UUID id, boolean published) {
        return jdbc.update("""
                UPDATE vehicle_unit SET published = :published,
                    status = CASE WHEN :published THEN 'AVAILABLE' ELSE 'DRAFT' END,
                    updated_at = CURRENT_TIMESTAMP WHERE id = :id
                """, new MapSqlParameterSource().addValue("id", id).addValue("published", published)) > 0;
    }

    public record InventoryRow(UUID id, UUID modelId, String brand, String name, String chassis,
                               BigDecimal price, String condition, String status,
                               boolean published, String description) {}
}
