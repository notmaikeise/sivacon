package br.com.sivacon.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.sivacon.dao.VehicleCatalogDao;
import br.com.sivacon.dao.VehicleCatalogDao.VehicleRow;

@Service
public class VehicleCatalogService {

    private final VehicleCatalogDao dao;

    public VehicleCatalogService(VehicleCatalogDao dao) {
        this.dao = dao;
    }

    public List<VehicleRow> list(String brand, BigDecimal maxPrice) {
        return dao.findPublishedAvailable(brand, maxPrice);
    }

    public VehicleRow find(UUID id) {
        return dao.findPublishedById(id);
    }
}
