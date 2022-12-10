package at.fhtw.swen3.services;

import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.services.dto.Warehouse;

public interface WarehouseService {
    public void importWarehouse(Warehouse warehouse);
    public Warehouse exportWarehouse();
}
