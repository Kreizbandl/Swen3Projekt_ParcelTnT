package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.services.dto.GeoCoordinate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GeoCoordinateMapperTest {

    @Test
    public void dtoToEntity(){
        GeoCoordinate geoCoordinate = new GeoCoordinate();
        geoCoordinate.setLat(12.12);
        geoCoordinate.setLon(34.34);

        GeoCoordinateEntity geoCoordinateEntity = GeoCoordinateMapper.INSTANCE.dtoToEntity(geoCoordinate);

        assertEquals(geoCoordinate.getLat(),geoCoordinateEntity.getLat());
        assertEquals(geoCoordinate.getLon(),geoCoordinateEntity.getLon());
    }

    @Test
    public void entityToDto(){
        GeoCoordinateEntity geoCoordinateEntity = GeoCoordinateEntity.builder().lat(12.12).lon(34.34).build();

        GeoCoordinate geoCoordinate = GeoCoordinateMapper.INSTANCE.entityToDto(geoCoordinateEntity);

        assertEquals(geoCoordinateEntity.getLat(),geoCoordinate.getLat());
        assertEquals(geoCoordinateEntity.getLon(),geoCoordinate.getLon());
    }
}