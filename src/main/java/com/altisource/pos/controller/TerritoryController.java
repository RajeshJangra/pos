package com.altisource.pos.controller;

import com.altisource.pos.domain.Territory;
import com.altisource.pos.exception.TerritoryValidationException;
import com.altisource.pos.service.TerritoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


/**
 * Created by rajeshkumar on 07/04/17.
 */
@RestController
@RequestMapping(value = "/territories", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class TerritoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TerritoryController.class);
    @Autowired
    private TerritoryService territoryService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Territory getUser(@PathVariable long id) throws TerritoryValidationException {
        return territoryService.getTerritory(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createTerritory(@RequestBody Territory territory) throws TerritoryValidationException {
        territoryService.createTerritory(territory);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateTerritory(@RequestBody Territory territory) throws TerritoryValidationException {
        territoryService.updateTerritory(territory);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.CREATED)
    public void deleteTerritory(@PathVariable long id) throws TerritoryValidationException {
        territoryService.deleteTerritory(id);
    }


    @ExceptionHandler(TerritoryValidationException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(TerritoryValidationException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
