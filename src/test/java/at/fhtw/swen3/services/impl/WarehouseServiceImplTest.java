package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.repositories.RecipientRepository;
import at.fhtw.swen3.persistence.repositories.WarehouseRepository;
import at.fhtw.swen3.services.WarehouseService;
import at.fhtw.swen3.services.dto.Warehouse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseServiceImplTest {

    @Mock
    private WarehouseService warehouseService;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
        //WarehouseService warehouseService = new WarehouseServiceImpl(warehouseRepository);
    }

    @Test
    void importWarehouse() {
    }

    @Test
    void exportWarehouse() {
        Warehouse warehouse = warehouseService.exportWarehouse();
        assertNotNull(warehouse);
    }

    @Test
    void getWarehouseOrTruckByCode() {
    }
}