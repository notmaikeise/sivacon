package br.com.sivacon.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.sivacon.dao.VehicleCatalogDao;
import br.com.sivacon.dao.VehicleCatalogDao.VehicleRow;

@ExtendWith(MockitoExtension.class)
class VehicleCatalogServiceTest {

    @Mock
    private VehicleCatalogDao dao;

    @InjectMocks
    private VehicleCatalogService service;

    @Test
    void listsOnlyDataProvidedByCatalogDao() {
        var vehicle = new VehicleRow(UUID.randomUUID(), "Honda", "CG 160 Titan",
                new BigDecimal("18990.00"), "NEW", "AVAILABLE", "Teste");
        when(dao.findPublishedAvailable("Honda", new BigDecimal("20000")))
                .thenReturn(List.of(vehicle));

        var result = service.list("Honda", new BigDecimal("20000"));

        assertThat(result).containsExactly(vehicle);
        verify(dao).findPublishedAvailable("Honda", new BigDecimal("20000"));
    }
}
