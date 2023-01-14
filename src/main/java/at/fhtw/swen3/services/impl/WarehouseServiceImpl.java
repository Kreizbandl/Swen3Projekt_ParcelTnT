package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.*;
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
        warehouseNextHopsRepository.deleteAll();
        hopRepository.deleteAll();
        errorRepository.deleteAll();
        geoCoordinateRepository.deleteAll();
        hopArrivalRepository.deleteAll();
        parcelRepository.deleteAll();
        recipientRepository.deleteAll();
        transferwarehouseRepository.deleteAll();
        truckRepository.deleteAll();
        warehouseRepository.deleteAll();

        //store data in db
        //log.info("Importing warehosues after mapping: " + warehouse);
        //TODO this doesn't store the entire load in the db -> probably mistakes in the @Entities with the @ManyToOne etc.
        //warehouseRepository.save(warehouseEntity);//too large for db request
        //System.out.println("warehosue " + warehouse.toString());
        //System.out.println("warehosueEntity " + warehouseEntity.toString());
        this.saveWarehouseHierarchyDatabase(warehouseEntity);
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

    private void saveWarehouseHierarchyDatabase(WarehouseEntity warehouseEntity){
        for(WarehouseNextHopsEntity warehouseNextHopsEntity : warehouseEntity.getNextHops()){
            this.saveHopManager(warehouseNextHopsEntity.getHop());
        }
        warehouseRepository.save(warehouseEntity);
    }

    private void saveHopManager(HopEntity hopEntity){
        if(hopEntity instanceof TruckEntity truckEntity){
            this.saveTruckInDatabase(truckEntity);
        }else if(hopEntity instanceof WarehouseEntity warehouseEntity){
            this.saveWarehouseInDatabase(warehouseEntity);
        }else if(hopEntity instanceof TransferwarehouseEntity transferwarehouseEntity){
            this.saveTransferwarehouseInDatabase(transferwarehouseEntity);
        }
    }

    private void saveTruckInDatabase(TruckEntity truckEntity){
        System.out.println("------->truck: " + truckEntity);
        //geoCoordinateRepository.save(truckEntity.getLocationCoordinates());
        truckRepository.save(truckEntity);
    }

    private void saveWarehouseInDatabase(WarehouseEntity warehouseEntity){
        System.out.println("------->warehouse: ...");
        this.saveWarehouseHierarchyDatabase(warehouseEntity);
    }

    private void saveTransferwarehouseInDatabase(TransferwarehouseEntity transferwarehouseEntity){
        System.out.println("------->transferwarehouse: " + transferwarehouseEntity);
        transferwarehouseRepository.save(transferwarehouseEntity);
    }

}
