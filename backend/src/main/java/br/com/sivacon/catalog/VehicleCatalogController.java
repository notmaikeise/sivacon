package br.com.sivacon.catalog;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.sivacon.dao.VehicleCatalogDao.VehicleRow;
import br.com.sivacon.service.VehicleCatalogService;

@RestController
@RequestMapping("/api/v1/catalog")
@CrossOrigin(origins = {"http://localhost:5173", "http://127.0.0.1:5173"})
public class VehicleCatalogController {

    private final VehicleCatalogService service;

    public VehicleCatalogController(VehicleCatalogService service) {
        this.service = service;
    }

    @GetMapping
    public List<VehicleSummary> list(
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) String condition,
            @RequestParam(required = false) String category) {
        return service.list(brand, minPrice, maxPrice, condition, category).stream()
                .map(VehicleCatalogController::toSummary).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleSummary> get(@PathVariable UUID id) {
        return java.util.Optional.ofNullable(service.find(id)).map(VehicleCatalogController::toSummary)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    private static VehicleSummary toSummary(VehicleRow row) {
        return new VehicleSummary(row.id(), row.brand(), row.name(), row.engine(), row.category(),
                row.price(), row.condition(), row.status(), row.published(),
                row.published() && "AVAILABLE".equals(row.status()), row.description());
    }

    public record VehicleSummary(UUID id, String brand, String name, String engine, String category,
                                 BigDecimal price, String condition, String status, boolean published,
                                 boolean available, String description) {
    }
}
