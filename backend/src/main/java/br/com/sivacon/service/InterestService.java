package br.com.sivacon.service;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import br.com.sivacon.dao.InterestDao;
import br.com.sivacon.dao.VehicleCatalogDao;

@Service
public class InterestService {

    private final VehicleCatalogDao vehicleDao;
    private final InterestDao interestDao;

    public InterestService(VehicleCatalogDao vehicleDao, InterestDao interestDao) {
        this.vehicleDao = vehicleDao;
        this.interestDao = interestDao;
    }

    @Transactional
    public UUID create(UUID vehicleId, String name, String email, String phone, String message) {
        return create(vehicleId, name, email, phone, message, null);
    }

    @Transactional
    public UUID create(UUID vehicleId, String name, String email, String phone, String message,
                       UUID userId) {
        if (!vehicleDao.isPublishedAndAvailable(vehicleId)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "A moto não está disponível para interesse.");
        }
        var buyerId = userId == null ? null : interestDao.findBuyerIdByUser(userId);
        if (buyerId == null) {
            buyerId = UUID.randomUUID();
            interestDao.insertBuyer(buyerId, name, email, phone);
        }

        var opportunityId = UUID.randomUUID();
        interestDao.insertOpportunity(opportunityId, buyerId, vehicleId);
        interestDao.insertInteraction(opportunityId, message);
        return opportunityId;
    }
}
