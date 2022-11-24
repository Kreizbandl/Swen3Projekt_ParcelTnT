package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.WarehouseNextHopsEntity;
import at.fhtw.swen3.services.dto.GeoCoordinate;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.WarehouseNextHops;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WarehouseNextHopsMapperTest {

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
        WarehouseNextHops warehouseNextHopsDto = new WarehouseNextHops();
        warehouseNextHopsDto.setTraveltimeMins(123);
        warehouseNextHopsDto.setHop(hop);

        WarehouseNextHopsEntity warehouseNextHops = WarehouseNextHopsMapper.INSTANCE.dtoToEntity(warehouseNextHopsDto);

        assertEquals("123",warehouseNextHops.getTraveltimeMins().toString());
        assertEquals("some type...",warehouseNextHops.getHop().getHopType());
        assertEquals("some code...",warehouseNextHops.getHop().getCode());
        assertEquals("some description...",warehouseNextHops.getHop().getDescription());
        assertEquals("3",warehouseNextHops.getHop().getProcessingDelayMins().toString());
        assertEquals("some name...",warehouseNextHops.getHop().getLocationName());
        assertEquals("3.3",warehouseNextHops.getHop().getLocationCoordinates().getLon().toString());
        assertEquals("4.4",warehouseNextHops.getHop().getLocationCoordinates().getLat().toString());
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

        WarehouseNextHops warehouseNextHopsDto = WarehouseNextHopsMapper.INSTANCE.entityToDto(warehouseNextHops);

        assertEquals("123",warehouseNextHopsDto.getTraveltimeMins().toString());
        assertEquals("some type...",warehouseNextHopsDto.getHop().getHopType());
        assertEquals("some code...",warehouseNextHopsDto.getHop().getCode());
        assertEquals("some description...",warehouseNextHopsDto.getHop().getDescription());
        assertEquals("3",warehouseNextHopsDto.getHop().getProcessingDelayMins().toString());
        assertEquals("some name...",warehouseNextHopsDto.getHop().getLocationName());
        assertEquals("3.3",warehouseNextHopsDto.getHop().getLocationCoordinates().getLon().toString());
        assertEquals("4.4",warehouseNextHopsDto.getHop().getLocationCoordinates().getLat().toString());
    }
}