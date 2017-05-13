package com.example.pos.service;

import com.example.pos.domain.Territory;
import com.example.pos.exception.PosApplicationException;
import com.example.pos.repository.TerritoryRepository;
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
    private TerritoryRepository territoryRepository;

    public Territory createTerritory(final Territory territory) throws PosApplicationException {
        if (territoryRepository.exists(territory.getId())) {
            String message = "Territory: " + territory.getId() + " already exists";
            LOGGER.error(message);
            throw new PosApplicationException(message);
        }
        return territoryRepository.save(territory);
    }

    public Territory updateTerritory(final Territory territory) throws PosApplicationException {
        if (territoryRepository.exists(territory.getId())) {
            return territoryRepository.save(territory);
        }
        String message = "Territory: " + territory.getId() + " does not exist";
        LOGGER.error(message);
        throw new PosApplicationException(message);
    }

    public Territory getTerritory(final long id) {
        return territoryRepository.findOne(id);
    }
}
