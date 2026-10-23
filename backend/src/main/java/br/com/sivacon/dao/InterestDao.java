package br.com.sivacon.dao;

import java.util.UUID;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class InterestDao {

    private final NamedParameterJdbcTemplate jdbc;

    public InterestDao(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void insertBuyer(UUID id, String name, String email, String phone) {
        jdbc.update("""
                INSERT INTO buyer (id, full_name, email, phone)
                VALUES (:id, :name, :email, :phone)
                """, new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("name", name)
                .addValue("email", email)
                .addValue("phone", phone));
    }

    public void insertOpportunity(UUID id, UUID buyerId, UUID vehicleId) {
        jdbc.update("""
                INSERT INTO service_opportunity
                    (id, buyer_id, vehicle_unit_id, source, stage)
                VALUES (:id, :buyerId, :vehicleId, 'CATALOG', 'NEW')
                """, new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("buyerId", buyerId)
                .addValue("vehicleId", vehicleId));
    }

    public void insertInteraction(UUID opportunityId, String message) {
        jdbc.update("""
                INSERT INTO service_interaction
                    (id, opportunity_id, channel, content)
                VALUES (:id, :opportunityId, 'FORM', :message)
                """, new MapSqlParameterSource()
                .addValue("id", UUID.randomUUID())
                .addValue("opportunityId", opportunityId)
                .addValue("message", message));
    }
}
