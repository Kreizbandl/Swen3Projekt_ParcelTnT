package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.TruckEntity;
import at.fhtw.swen3.services.dto.Truck;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TruckMapperTest {

    @Test
    public void dtoToEntity(){
        Truck truck = new Truck();
        truck.setRegionGeoJson("some geo...");
        truck.setNumberPlate("1234");

        TruckEntity truckEntity = TruckMapper.INSTANCE.dtoToEntity(truck);

        assertEquals(truck.getRegionGeoJson(),truckEntity.getRegionGeoJson());
        assertEquals(truck.getNumberPlate(),truckEntity.getNumberPlate());
    }

    /*@Test
    public void entityToDto(){
        TruckEntity truckEntity = TruckEntity.builder()
                .numberPlate("A").regionGeoJson("A").build();

        Truck truck = TruckMapper.INSTANCE.entityToDto(truckEntity);

        assertEquals(truck.getRegionGeoJson(),truckEntity.getRegionGeoJson());
        assertEquals(truck.getNumberPlate(),truckEntity.getNumberPlate());
    }*/
}