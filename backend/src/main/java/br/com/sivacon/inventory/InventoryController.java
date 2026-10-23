package br.com.sivacon.inventory;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.sivacon.dao.InventoryDao.InventoryRow;
import br.com.sivacon.service.InventoryService;

@RestController
@RequestMapping("/api/v1/inventory")
@CrossOrigin(origins = {"http://localhost:5173", "http://127.0.0.1:5173"})
public class InventoryController {
    private final InventoryService service;

    public InventoryController(InventoryService service) { this.service = service; }

    @GetMapping
    public List<InventoryRow> list() { return service.list(); }

    @PostMapping
    public InventoryCreated create(@Valid @RequestBody InventoryRequest request) {
        return new InventoryCreated(service.create(request.brand(), request.name(),
                request.engine(), request.category(), request.chassis(), request.price(),
                request.condition(), request.description()));
    }

    @PatchMapping("/{id}/publication")
    public void publish(@PathVariable UUID id, @Valid @RequestBody PublicationRequest request) {
        service.publish(id, request.published());
    }

    public record InventoryRequest(
            @NotBlank @Size(max = 100) String brand,
            @NotBlank @Size(max = 120) String name,
            @Size(max = 120) String engine,
            @Size(max = 80) String category,
            @NotBlank @Size(max = 80) String chassis,
            @NotNull @DecimalMin("0.00") BigDecimal price,
            @NotBlank String condition,
            @Size(max = 2000) String description) {}

    public record PublicationRequest(@NotNull Boolean published) {}
    public record InventoryCreated(UUID id) {}
}
