package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.*;
import at.fhtw.swen3.services.dto.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WarehouseMapperTest {

    @Test
    public void dtoToEntity(){
        GeoCoordinate geoCoordinate = new GeoCoordinate();
        geoCoordinate.setLon(3.3);
        geoCoordinate.setLat(4.4);
        Hop hop = new Hop();
        hop.setHopType("some type...");
        hop.setCode("some code...");
        hop.setDescription("some description...");
        hop.setProcessingDelayMins(3);
        hop.setLocationName("some name...");
        hop.setLocationCoordinates(geoCoordinate);
        WarehouseNextHops warehouseNextHops = new WarehouseNextHops();
        warehouseNextHops.setTraveltimeMins(123);
        warehouseNextHops.setHop(hop);
        List<WarehouseNextHops> warehouseNextHopsList = new ArrayList<>();
        warehouseNextHopsList.add(warehouseNextHops);
        Warehouse warehouseDto = new Warehouse();
        warehouseDto.setLevel(99);
        warehouseDto.setNextHops(warehouseNextHopsList);

        WarehouseEntity warehouse = WarehouseMapper.INSTANCE.dtoToEntity(warehouseDto);

        assertEquals("99",warehouse.getLevel().toString());
        assertEquals("123",warehouse.getNextHops().get(0).getTraveltimeMins().toString());
        assertEquals("some type...",warehouse.getNextHops().get(0).getHop().getHopType());
        assertEquals("some code...",warehouse.getNextHops().get(0).getHop().getCode());
        assertEquals("some description...",warehouse.getNextHops().get(0).getHop().getDescription());
        assertEquals("3",warehouse.getNextHops().get(0).getHop().getProcessingDelayMins().toString());
        assertEquals("some name...",warehouse.getNextHops().get(0).getHop().getLocationName());
        assertEquals("3.3",warehouse.getNextHops().get(0).getHop().getLocationCoordinates().getLon().toString());
        assertEquals("4.4",warehouse.getNextHops().get(0).getHop().getLocationCoordinates().getLat().toString());
    }

    @Test
    public void entityToDto(){
        GeoCoordinateEntity geoCoordinate = new GeoCoordinateEntity();
        geoCoordinate.setLon(3.3);
        geoCoordinate.setLat(4.4);
        HopEntity hop = new HopEntity();
        hop.setHopType("some type...");
        hop.setCode("some code...");
        hop.setDescription("some description...");
        hop.setProcessingDelayMins(3);
        hop.setLocationName("some name...");
        hop.setLocationCoordinates(geoCoordinate);
        WarehouseNextHopsEntity warehouseNextHops = new WarehouseNextHopsEntity();
        warehouseNextHops.setTraveltimeMins(123);
        warehouseNextHops.setHop(hop);
        List<WarehouseNextHopsEntity> warehouseNextHopsList = new ArrayList<>();
        warehouseNextHopsList.add(warehouseNextHops);
        WarehouseEntity warehouse = new WarehouseEntity();
        warehouse.setLevel(99);
        warehouse.setNextHops(warehouseNextHopsList);

        Warehouse warehouseDto = WarehouseMapper.INSTANCE.entityToDto(warehouse);

        assertEquals("99",warehouseDto.getLevel().toString());
        assertEquals("123",warehouseDto.getNextHops().get(0).getTraveltimeMins().toString());
        assertEquals("some type...",warehouseDto.getNextHops().get(0).getHop().getHopType());
        assertEquals("some code...",warehouseDto.getNextHops().get(0).getHop().getCode());
        assertEquals("some description...",warehouseDto.getNextHops().get(0).getHop().getDescription());
        assertEquals("3",warehouseDto.getNextHops().get(0).getHop().getProcessingDelayMins().toString());
        assertEquals("some name...",warehouseDto.getNextHops().get(0).getHop().getLocationName());
        assertEquals("3.3",warehouseDto.getNextHops().get(0).getHop().getLocationCoordinates().getLon().toString());
        assertEquals("4.4",warehouseDto.getNextHops().get(0).getHop().getLocationCoordinates().getLat().toString());
    }
}