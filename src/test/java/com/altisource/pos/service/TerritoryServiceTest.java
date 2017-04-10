package com.altisource.pos.service;

import com.altisource.pos.domain.Category;
import com.altisource.pos.domain.Territory;
import com.altisource.pos.exception.PosApplicationException;
import com.altisource.pos.repository.TerritoryRepository;
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
public class TerritoryServiceTest {

    @InjectMocks
    TerritoryService territoryService;
    @Mock
    TerritoryRepository territoryRepository;

    @Test
    public void testCreateTerritorySuccessful() throws Exception {
        long territoryId = 1l;
        Territory expected = getTerritory(territoryId);
        when(territoryRepository.exists(expected.getId())).thenReturn(false);
        when(territoryRepository.save(expected)).thenReturn(expected);
        Territory returned = territoryService.createTerritory(expected);
        assertEquals(expected, returned);
    }

    @Test(expected = PosApplicationException.class)
    public void testCreateTerritoryAndTerritoryAlreadyExists() throws Exception {
        long territoryId = 1l;
        Territory expected = getTerritory(territoryId);
        when(territoryRepository.exists(expected.getId())).thenReturn(true);
        territoryService.createTerritory(expected);
    }

    @Test
    public void testUpdateTerritorySuccessful() throws Exception {
        long territoryId = 1l;
        Territory expected = getTerritory(territoryId);
        when(territoryRepository.exists(expected.getId())).thenReturn(true);
        when(territoryRepository.save(expected)).thenReturn(expected);
        Territory returned = territoryService.updateTerritory(expected);
        assertEquals(expected, returned);
    }

    @Test(expected = PosApplicationException.class)
    public void testUpdateTerritoryAndTerritoryAlreadyExists() throws Exception {
        long territoryId = 1l;
        Territory expected = getTerritory(territoryId);
        when(territoryRepository.exists(expected.getId())).thenReturn(false);
        territoryService.updateTerritory(expected);
    }

    @Test
    public void testGetTerritorySuccessful() throws Exception {
        long territoryId = 1l;
        Territory expected = getTerritory(territoryId);
        when(territoryRepository.findOne(expected.getId())).thenReturn(expected);
        Territory returned = territoryService.getTerritory(expected.getId());
        assertEquals(expected, returned);
    }

    private Territory getTerritory(long territoryId){
        Territory delhiTerritory = new Territory(20, "Delhi", "Delhi Territory");
        delhiTerritory.setId(territoryId);
        return delhiTerritory;
    }
}
