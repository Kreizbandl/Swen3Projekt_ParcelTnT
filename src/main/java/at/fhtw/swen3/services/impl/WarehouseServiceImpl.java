package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.persistence.repositories.GeoCoordinateRepository;
import at.fhtw.swen3.persistence.repositories.HopRepository;
import at.fhtw.swen3.persistence.repositories.WarehouseNextHopsRepository;
import at.fhtw.swen3.persistence.repositories.WarehouseRepository;
import at.fhtw.swen3.services.WarehouseService;
import at.fhtw.swen3.services.dto.Warehouse;
import at.fhtw.swen3.services.mapper.GeoCoordinateMapper;
import at.fhtw.swen3.services.mapper.WarehouseMapper;
import at.fhtw.swen3.services.mapper.WarehouseMapperImpl;
import at.fhtw.swen3.services.validation.Validator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor @Slf4j
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private WarehouseNextHopsRepository warehouseNextHopsRepository;
    @Autowired
    private HopRepository hopRepository;

    private final Validator validator;



    @Override
    public void importWarehouse(Warehouse warehouse) {
        validator.validate(warehouse);
        log.info("Importing warehouse " + warehouse);
        WarehouseEntity warehouseEntity = WarehouseMapper.INSTANCE.dtoToEntity(warehouse);
        warehouseRepository.save(warehouseEntity);
    }

    @Override
    public Warehouse exportWarehouse() {
        log.info("Exporting warehouse");

        List<Warehouse> warehouseList = new ArrayList<>();
        List<WarehouseEntity> warehouseEntities = warehouseRepository.findAll();
        WarehouseMapperImpl warehouseMapper = new WarehouseMapperImpl();

        for (WarehouseEntity warehouseEntity : warehouseEntities){
            warehouseList.add(warehouseMapper.entityToDto(warehouseEntity));
        }

        return warehouseList.get(0);
    }
}
