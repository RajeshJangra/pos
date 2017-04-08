package com.altisource.pos.service;

import com.altisource.pos.domain.Territory;
import com.altisource.pos.exception.TerritoryValidationException;
import com.altisource.pos.repository.TerritoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by rajeshkumar on 07/04/17.
 */
@Service
public class TerritoryService {

    @Autowired
    TerritoryRepository territoryRepository;

    public List<Territory> getTerritories() {
        return territoryRepository.findAll().stream().collect(Collectors.toList());
    }

    public void createTerritory(final Territory territory) throws TerritoryValidationException {
        if (territoryRepository.exists(territory.getId())) {
            throw new TerritoryValidationException("Territory already exists");
        }
        territoryRepository.save(territory);
    }

    public void updateTerritory(final Territory territory) throws TerritoryValidationException {
        if (territoryRepository.exists(territory.getId())) {
            territoryRepository.save(territory);
        }
        throw new TerritoryValidationException("Territory does not exist");
    }

    public void deleteTerritory(final long id) throws TerritoryValidationException {
        if (territoryRepository.exists(id)) {
            territoryRepository.delete(id);
        }
        throw new TerritoryValidationException("Territory does not exist");
    }

    public Territory getTerritory(final long id) {
        return territoryRepository.findOne(id);
    }
}
