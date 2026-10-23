package br.com.sivacon.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import br.com.sivacon.dao.InventoryDao;
import br.com.sivacon.dao.InventoryDao.InventoryRow;

@Service
public class InventoryService {
    private final InventoryDao dao;

    public InventoryService(InventoryDao dao) { this.dao = dao; }

    public List<InventoryRow> list() { return dao.findAll(); }

    @Transactional
    public UUID create(String brand, String name, String engine, String category, String chassis,
                       BigDecimal price, String condition, String description) {
        if (price.signum() < 0) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "O preço não pode ser negativo.");
        try {
            var modelId = dao.findOrCreateModel(brand.trim(), name.trim(), engine, category);
            return dao.insertUnit(modelId, chassis.trim(), price, condition, description);
        } catch (DuplicateKeyException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "O chassi já está cadastrado.");
        }
    }

    public void publish(UUID id, boolean published) {
        if (!dao.setPublished(id, published)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unidade não encontrada.");
        }
    }
}
