package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.persistence.repositories.*;
import at.fhtw.swen3.services.WarehouseService;
import at.fhtw.swen3.services.dto.Warehouse;
import at.fhtw.swen3.services.mapper.WarehouseMapper;
import at.fhtw.swen3.services.mapper.WarehouseMapperImpl;
import at.fhtw.swen3.services.validation.Validator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;
    //TODO find different solution for clearing db
    @Autowired
    private ErrorRepository errorRepository;
    @Autowired
    private GeoCoordinateRepository geoCoordinateRepository;
    @Autowired
    private HopArrivalRepository hopArrivalRepository;
    @Autowired
    private ParcelRepository parcelRepository;
    @Autowired
    private RecipientRepository recipientRepository;
    @Autowired
    private TransferwarehouseRepository transferwarehouseRepository;
    @Autowired
    private TruckRepository truckRepository;

    @Autowired
    private WarehouseNextHopsRepository warehouseNextHopsRepository;
    @Autowired
    private HopRepository hopRepository;

    private final Validator validator;

    @Override
    public void importWarehouse(Warehouse warehouse) {
        //validate the data
        //TODO handle validation errors ?
        validator.validate(warehouse);
        //log.info("Importing warehouse before mapping: " + warehouse)
        WarehouseEntity warehouseEntity = WarehouseMapper.INSTANCE.dtoToEntity(warehouse);

        //reset/clear entire db
        errorRepository.deleteAll();
        geoCoordinateRepository.deleteAll();
        hopArrivalRepository.deleteAll();
        parcelRepository.deleteAll();
        recipientRepository.deleteAll();
        transferwarehouseRepository.deleteAll();
        truckRepository.deleteAll();
        warehouseRepository.deleteAll();
        warehouseNextHopsRepository.deleteAll();
        hopRepository.deleteAll();

        //store data in db
        //log.info("Importing warehosues after mapping: " + warehouse);
        //TODO this doesn't store the entire load in the db -> probably mistakes in the @Entities with the @ManyToOne etc.
        //warehouseRepository.save(warehouseEntity);
        //System.out.println("warehosue " + warehouse.toString());
        System.out.println("warehosueEntity " + warehouseEntity.toString());
        warehouseRepository.save(warehouseEntity);//too large for db request
    }

    @Override
    public Warehouse exportWarehouse() {
        log.info("Exporting warehouse");

        //TODO warehouse is not just a list -> a warehouse has a warehouse...
        List<Warehouse> warehouseList = new ArrayList<>();
        List<WarehouseEntity> warehouseEntities = warehouseRepository.findAll();
        WarehouseMapperImpl warehouseMapper = new WarehouseMapperImpl();

        for (WarehouseEntity warehouseEntity : warehouseEntities){
            warehouseList.add(warehouseMapper.entityToDto(warehouseEntity));
        }

        return warehouseList.get(0);
    }


    public Optional<HopEntity> getHopById(Long id) {
        //TODO id != code -> we should find a warehouse/truck by code
        return hopRepository.findById(id);
    }



}
