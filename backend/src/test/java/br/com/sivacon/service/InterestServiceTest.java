package br.com.sivacon.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import br.com.sivacon.dao.InterestDao;
import br.com.sivacon.dao.VehicleCatalogDao;

@ExtendWith(MockitoExtension.class)
class InterestServiceTest {

    @Mock
    private VehicleCatalogDao vehicleDao;

    @Mock
    private InterestDao interestDao;

    @InjectMocks
    private InterestService service;

    @Test
    void rejectsInterestForUnavailableVehicle() {
        var vehicleId = UUID.randomUUID();
        when(vehicleDao.isPublishedAndAvailable(vehicleId)).thenReturn(false);

        assertThatThrownBy(() -> service.create(vehicleId, "Ana", "ana@example.com",
                null, "Tenho interesse."))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("moto não está disponível");
    }

    @Test
    void persistsBuyerOpportunityAndInteractionInOrder() {
        var vehicleId = UUID.randomUUID();
        when(vehicleDao.isPublishedAndAvailable(vehicleId)).thenReturn(true);

        var opportunityId = service.create(vehicleId, "Ana", "ana@example.com",
                "11999999999", "Tenho interesse.");

        assertThat(opportunityId).isNotNull();
        InOrder order = inOrder(interestDao);
        order.verify(interestDao).insertBuyer(org.mockito.ArgumentMatchers.any(), 
                org.mockito.ArgumentMatchers.eq("Ana"),
                org.mockito.ArgumentMatchers.eq("ana@example.com"),
                org.mockito.ArgumentMatchers.eq("11999999999"));
        order.verify(interestDao).insertOpportunity(org.mockito.ArgumentMatchers.eq(opportunityId),
                org.mockito.ArgumentMatchers.any(), org.mockito.ArgumentMatchers.eq(vehicleId));
        order.verify(interestDao).insertInteraction(opportunityId, "Tenho interesse.");
    }
}
