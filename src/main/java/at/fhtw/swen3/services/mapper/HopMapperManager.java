package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.TransferwarehouseEntity;
import at.fhtw.swen3.persistence.entities.TruckEntity;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.Transferwarehouse;
import at.fhtw.swen3.services.dto.Truck;
import at.fhtw.swen3.services.dto.Warehouse;

public abstract class HopMapperManager implements HopMapper{

    private final HopMapper hopMapper;

    protected HopMapperManager(HopMapper hopMapper){
        this.hopMapper = hopMapper;
    }

    @Override
    public HopEntity dtoToEntity(Hop hop) {
        if(hop instanceof Transferwarehouse transferwarehouse){
            return TransferwarehouseMapper.INSTANCE.dtoToEntity(transferwarehouse);
        }else if(hop instanceof Truck truck){
            return TruckMapper.INSTANCE.dtoToEntity(truck);
        }else if(hop instanceof Warehouse warehouse){
            return WarehouseMapper.INSTANCE.dtoToEntity(warehouse);
        }
        return hopMapper.dtoToEntity(hop);
    }

    @Override
    public Hop entityToDto(HopEntity hopEntity) {
        if(hopEntity instanceof TransferwarehouseEntity transferwarehouseEntity){
            return TransferwarehouseMapper.INSTANCE.entityToDto(transferwarehouseEntity);
        }else if(hopEntity instanceof TruckEntity truckEntity){
            return TruckMapper.INSTANCE.entityToDto(truckEntity);
        }else if(hopEntity instanceof WarehouseEntity warehouseEntity){
            return WarehouseMapper.INSTANCE.entityToDto(warehouseEntity);
        }
        return hopMapper.entityToDto(hopEntity);
    }
}
