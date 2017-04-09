package com.altisource.pos.controller;

import com.altisource.pos.domain.Territory;
import com.altisource.pos.exception.PosApplicationException;
import com.altisource.pos.service.TerritoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


/**
 * Created by rajeshkumar on 07/04/17.
 */
@RestController
@RequestMapping(value = "/territories", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class TerritoryController {

    @Autowired
    private TerritoryService territoryService;

    @RequestMapping(value = "/{id}", method = GET)
    @ResponseStatus(OK)
    public Territory getUser(@PathVariable long id) throws PosApplicationException {
        return territoryService.getTerritory(id);
    }

    @RequestMapping(method = POST)
    @ResponseStatus(CREATED)
    public void createTerritory(@RequestBody Territory territory) throws PosApplicationException {
        territoryService.createTerritory(territory);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(OK)
    public void updateTerritory(@RequestBody Territory territory) throws PosApplicationException {
        territoryService.updateTerritory(territory);
    }
}
