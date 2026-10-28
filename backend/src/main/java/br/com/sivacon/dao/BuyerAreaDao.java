package br.com.sivacon.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BuyerAreaDao {
    private final NamedParameterJdbcTemplate jdbc;

    public BuyerAreaDao(NamedParameterJdbcTemplate jdbc) { this.jdbc = jdbc; }

    public BuyerRow findBuyer(UUID userId) {
        return jdbc.query("""
                SELECT id, full_name, email, phone FROM buyer WHERE user_id = :userId
                """, new MapSqlParameterSource("userId", userId), (rs, n) -> new BuyerRow(
                rs.getObject("id", UUID.class), rs.getString("full_name"),
                rs.getString("email"), rs.getString("phone"))).stream().findFirst().orElse(null);
    }

    public List<InterestRow> findInterests(UUID userId) {
        return jdbc.query("""
                SELECT o.id, o.stage, o.source, o.created_at, m.brand, m.name
                FROM service_opportunity o
                JOIN buyer b ON b.id = o.buyer_id
                LEFT JOIN vehicle_unit u ON u.id = o.vehicle_unit_id
                LEFT JOIN vehicle_model m ON m.id = u.model_id
                WHERE b.user_id = :userId ORDER BY o.created_at DESC
                """, new MapSqlParameterSource("userId", userId), (rs, n) -> new InterestRow(
                rs.getObject("id", UUID.class), rs.getString("stage"), rs.getString("source"),
                rs.getObject("created_at", java.time.OffsetDateTime.class),
                rs.getString("brand"), rs.getString("name")));
    }

    public List<ProposalRow> findProposals(UUID userId) {
        return jdbc.query("""
                SELECT p.id, p.status, p.current_version, p.created_at,
                       m.brand, m.name, v.negotiated_price, v.valid_until, v.payment_method
                FROM proposal p
                JOIN buyer b ON b.id = p.buyer_id
                JOIN vehicle_unit u ON u.id = p.vehicle_unit_id
                JOIN vehicle_model m ON m.id = u.model_id
                JOIN proposal_version v ON v.proposal_id = p.id
                    AND v.version_number = p.current_version
                WHERE b.user_id = :userId ORDER BY p.created_at DESC
                """, new MapSqlParameterSource("userId", userId), (rs, n) -> new ProposalRow(
                rs.getObject("id", UUID.class), rs.getString("status"),
                rs.getInt("current_version"), rs.getObject("created_at", java.time.OffsetDateTime.class),
                rs.getString("brand"), rs.getString("name"), rs.getBigDecimal("negotiated_price"),
                rs.getObject("valid_until", java.time.LocalDate.class), rs.getString("payment_method")));
    }

    public List<PurchaseRow> findPurchases(UUID userId) {
        return jdbc.query("""
                SELECT s.id, s.status, s.confirmed_at, m.brand, m.name
                FROM sale s JOIN buyer b ON b.id = s.buyer_id
                JOIN vehicle_unit u ON u.id = s.vehicle_unit_id
                JOIN vehicle_model m ON m.id = u.model_id
                WHERE b.user_id = :userId ORDER BY s.confirmed_at DESC
                """, new MapSqlParameterSource("userId", userId), (rs, n) -> new PurchaseRow(
                rs.getObject("id", UUID.class), rs.getString("status"),
                rs.getObject("confirmed_at", java.time.OffsetDateTime.class),
                rs.getString("brand"), rs.getString("name")));
    }

    public record BuyerRow(UUID id, String fullName, String email, String phone) {}
    public record InterestRow(UUID id, String stage, String source,
                              java.time.OffsetDateTime createdAt, String brand, String name) {}
    public record ProposalRow(UUID id, String status, int currentVersion,
                              java.time.OffsetDateTime createdAt, String brand, String name,
                              BigDecimal negotiatedPrice, java.time.LocalDate validUntil,
                              String paymentMethod) {}
    public record PurchaseRow(UUID id, String status, java.time.OffsetDateTime confirmedAt,
                              String brand, String name) {}
}
