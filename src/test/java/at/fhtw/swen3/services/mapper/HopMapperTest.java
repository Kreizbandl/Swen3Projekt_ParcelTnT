package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.services.dto.GeoCoordinate;
import at.fhtw.swen3.services.dto.Hop;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HopMapperTest {

    @Test
    public void dtoToEntity(){
        GeoCoordinate geoCoordinate = new GeoCoordinate();
        geoCoordinate.setLon(3.3);
        geoCoordinate.setLat(4.4);

        Hop hop = new Hop();
        hop.setHopType("type");
        hop.setCode("1234");
        hop.setDescription("some desc...");
        hop.setLocationName("name");
        hop.setProcessingDelayMins(3);
        hop.setLocationCoordinates(geoCoordinate);

        HopEntity hopEntity = HopMapper.INSTANCE.dtoToEntity(hop);

        assertEquals(hopEntity.getHopType(),hop.getHopType());
        assertEquals(hopEntity.getCode(),hop.getCode());
        assertEquals(hopEntity.getDescription(),hop.getDescription());
        assertEquals(hopEntity.getProcessingDelayMins(),hop.getProcessingDelayMins());
        assertEquals(hopEntity.getLocationName(),hop.getLocationName());
        assertEquals(hopEntity.getLocationCoordinates().getLon(),hop.getLocationCoordinates().getLon());
        assertEquals(hopEntity.getLocationCoordinates().getLat(),hop.getLocationCoordinates().getLat());
    }

    @Test
    public void entityToDto(){
        GeoCoordinateEntity geoCoordinate = new GeoCoordinateEntity();
        geoCoordinate.setLon(3.3);
        geoCoordinate.setLat(4.4);

        HopEntity hopEntity = HopEntity.builder()
                .code("1234")
                .description("some desc...")
                .hopType("type")
                .processingDelayMins(3)
                .locationName("name")
                .locationCoordinates(geoCoordinate).build();

        Hop hop = HopMapper.INSTANCE.entityToDto(hopEntity);

        assertEquals(hopEntity.getHopType(),hop.getHopType());
        assertEquals(hopEntity.getCode(),hop.getCode());
        assertEquals(hopEntity.getDescription(),hop.getDescription());
        assertEquals(hopEntity.getProcessingDelayMins(),hop.getProcessingDelayMins());
        assertEquals(hopEntity.getLocationName(),hop.getLocationName());
        assertEquals(hopEntity.getLocationCoordinates().getLon(),hop.getLocationCoordinates().getLon());
        assertEquals(hopEntity.getLocationCoordinates().getLat(),hop.getLocationCoordinates().getLat());
    }
}