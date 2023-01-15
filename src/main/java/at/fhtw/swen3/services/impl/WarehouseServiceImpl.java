package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.*;
import at.fhtw.swen3.persistence.repositories.*;
import at.fhtw.swen3.services.WarehouseService;
import at.fhtw.swen3.services.dto.*;
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
        this.saveWarehouseHierarchyDatabase(warehouseEntity);
    }

    @Override
    public Warehouse exportWarehouse() {
        log.info("Exporting warehouses");

        //TODO warehouse is not just a list -> a warehouse has a warehouse...
        //List<Warehouse> warehouseList = new ArrayList<>();
        List<WarehouseEntity> warehouseEntities = warehouseRepository.findAll();
        //WarehouseMapperImpl warehouseMapper = new WarehouseMapperImpl();

        System.out.println("warehouseEntities ------> " + warehouseEntities);

        Warehouse warehouse = WarehouseMapper.INSTANCE.entityToDto(warehouseEntities.get(0));

        /*for (WarehouseEntity warehouseEntity : warehouseEntities){
            warehouseList.add(WarehouseMapper.INSTANCE.entityToDto(warehouseEntity));
            //warehouseList.add(warehouseMapper.entityToDto(warehouseEntity));
        }*/

        //System.out.println(warehouseList.get(0));

        return warehouse;
    }

    @Override
    public Hop getWarehouseOrTruckByCode(String code) {
        HopEntity hopEntity = hopRepository.getWarehouseOrTruckByCode(code);
        Hop hop = null;

        //different values -> there is probably a better solution
        if(hopEntity instanceof TruckEntity){
            hop = new Truck();
            ((Truck) hop).setNumberPlate(((TruckEntity) hopEntity).getNumberPlate());
            ((Truck) hop).setRegionGeoJson(((TruckEntity) hopEntity).getRegionGeoJson());
        }else if(hopEntity instanceof WarehouseEntity){
            hop = new Warehouse();
            ((Warehouse) hop).setLevel(((WarehouseEntity) hopEntity).getLevel());
            ((Warehouse) hop).setNextHops(null);
        }else if(hopEntity instanceof TransferwarehouseEntity){
            hop = new Transferwarehouse();
            ((Transferwarehouse) hop).setRegionGeoJson(((TransferwarehouseEntity) hopEntity).getRegionGeoJson());
            ((Transferwarehouse) hop).setLogisticsPartner(((TransferwarehouseEntity) hopEntity).getLogisticsPartner());
            ((Transferwarehouse) hop).setLogisticsPartnerUrl(((TransferwarehouseEntity) hopEntity).getLogisticsPartnerUrl());
        }

        //same values for each hop type
        hop.setCode(hopEntity.getCode());
        hop.setHopType(hopEntity.getHopType());
        hop.setDescription(hopEntity.getDescription());
        hop.setLocationName(hopEntity.getLocationName());
        hop.setProcessingDelayMins(hopEntity.getProcessingDelayMins());
        GeoCoordinate geo = new GeoCoordinate();
            geo.setLon(hopEntity.getLocationCoordinates().getLon());
            geo.setLat(hopEntity.getLocationCoordinates().getLat());
        hop.setLocationCoordinates(geo);

        return hop;
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
