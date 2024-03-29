package at.fhtw.swen3.services;

import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.Warehouse;

import java.util.Optional;

public interface WarehouseService {
    public void importWarehouse(Warehouse warehouse);
    public Warehouse exportWarehouse();

    Hop getWarehouseOrTruckByCode(String code);
}
