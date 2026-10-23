package br.com.sivacon.interest;

import java.util.UUID;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.sivacon.service.InterestService;

@RestController
@RequestMapping("/api/v1/interests")
@CrossOrigin(origins = {"http://localhost:5173", "http://127.0.0.1:5173"})
public class InterestController {

    private final InterestService service;

    public InterestController(InterestService service) {
        this.service = service;
    }

    @PostMapping
    public InterestResponse create(@Valid @RequestBody InterestRequest request) {
        var opportunityId = service.create(request.vehicleId(), request.name(), request.email(),
                request.phone(), request.message());
        return new InterestResponse(opportunityId, "Interesse registrado com sucesso.");
    }

    public record InterestRequest(
            @NotNull UUID vehicleId,
            @NotBlank @Size(max = 160) String name,
            @NotBlank @Email @Size(max = 254) String email,
            @Size(max = 32) String phone,
            @NotBlank @Size(max = 2000) String message) {
    }

    public record InterestResponse(UUID opportunityId, String message) {
    }
}
