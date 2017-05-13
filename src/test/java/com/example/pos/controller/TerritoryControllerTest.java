package com.example.pos.controller;

import com.example.pos.domain.Territory;
import com.example.pos.exception.PosApplicationException;
import com.example.pos.service.TerritoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * BillService Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Apr 10, 2017</pre>
 */
@RunWith(MockitoJUnitRunner.class)
public class TerritoryControllerTest {

    @InjectMocks
    private TerritoryController territoryController;
    @Mock
    private TerritoryService territoryService;

    @Test
    public void testCreateTerritorySuccessful() throws Exception {
        long territoryId = 1L;
        Territory expected = getTerritory(territoryId);
        when(territoryService.createTerritory(expected)).thenReturn(expected);
        Territory returned = territoryController.createTerritory(expected);
        assertEquals(expected, returned);
    }

    @Test(expected = PosApplicationException.class)
    public void testCreateTerritoryAndTerritoryAlreadyExists() throws Exception {
        long territoryId = 1L;
        Territory expected = getTerritory(territoryId);
        when(territoryService.createTerritory(expected)).thenThrow(new PosApplicationException("Territory: " + expected.getId() + " already exists"));
        territoryController.createTerritory(expected);
    }

    @Test
    public void testUpdateTerritorySuccessful() throws Exception {
        long territoryId = 1L;
        Territory expected = getTerritory(territoryId);
        when(territoryService.updateTerritory(expected)).thenReturn(expected);
        Territory returned = territoryController.updateTerritory(expected);
        assertEquals(expected, returned);
    }

    @Test(expected = PosApplicationException.class)
    public void testUpdateTerritoryAndTerritoryAlreadyExists() throws Exception {
        long territoryId = 1L;
        Territory expected = getTerritory(territoryId);
        when(territoryService.updateTerritory(expected)).thenThrow(new PosApplicationException("Territory: " + expected.getId() + " already exists"));
        territoryController.updateTerritory(expected);
    }

    @Test
    public void testGetTerritorySuccessful() throws Exception {
        long territoryId = 1L;
        Territory expected = getTerritory(territoryId);
        when(territoryService.getTerritory(expected.getId())).thenReturn(expected);
        Territory returned = territoryController.getTerritory(expected.getId());
        assertEquals(expected, returned);
    }

    private Territory getTerritory(long territoryId) {
        Territory delhiTerritory = new Territory(20, "Delhi", "Delhi Territory");
        delhiTerritory.setId(territoryId);
        return delhiTerritory;
    }
}
