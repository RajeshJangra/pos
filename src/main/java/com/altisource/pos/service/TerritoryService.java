package com.altisource.pos.service;

import com.altisource.pos.domain.Territory;
import com.altisource.pos.exception.PosApplicationException;
import com.altisource.pos.repository.TerritoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by rajeshkumar on 07/04/17.
 */
@Service
public class TerritoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TerritoryService.class);

    @Autowired
    TerritoryRepository territoryRepository;

    public void createTerritory(final Territory territory) throws PosApplicationException {
        if (territoryRepository.exists(territory.getId())) {
            String message = "Territory: " + territory.getId() + " already exists";
            LOGGER.error(message);
            throw new PosApplicationException(message);
        }
        territoryRepository.save(territory);
    }

    public void updateTerritory(final Territory territory) throws PosApplicationException {
        if (territoryRepository.exists(territory.getId())) {
            territoryRepository.save(territory);
        }
        String message = "Territory: " + territory.getId() + " does not exist";
        LOGGER.error(message);
        throw new PosApplicationException(message);
    }

    public Territory getTerritory(final long id) {
        return territoryRepository.findOne(id);
    }
}
