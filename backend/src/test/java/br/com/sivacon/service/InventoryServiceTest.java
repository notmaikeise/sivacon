package br.com.sivacon.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import br.com.sivacon.dao.InventoryDao;

@ExtendWith(MockitoExtension.class)
class InventoryServiceTest {

    @Mock
    private InventoryDao dao;

    @InjectMocks
    private InventoryService service;

    @Test
    void rejectsNegativePriceBeforeWriting() {
        assertThatThrownBy(() -> service.create("Honda", "CG", null, null, "CH-1",
                new BigDecimal("-1"), "NEW", null))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("preço não pode ser negativo");
    }

    @Test
    void publishesExistingUnit() {
        var id = UUID.randomUUID();
        when(dao.setPublished(id, true)).thenReturn(true);

        service.publish(id, true);

        verify(dao).setPublished(id, true);
    }
}
