package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.TransferwarehouseEntity;
import at.fhtw.swen3.services.dto.Transferwarehouse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransferwarehouseMapperTest {

    @Test
    public void dtoToEntity(){
        Transferwarehouse transferwarehouse = new Transferwarehouse();
        transferwarehouse.setLogisticsPartner("A");
        transferwarehouse.setLogisticsPartnerUrl("A");
        transferwarehouse.setRegionGeoJson("A");

        TransferwarehouseEntity transferwarehouseEntity = TransferwarehouseMapper.INSTANCE.dtoToEntity(transferwarehouse);

        assertEquals(transferwarehouse.getLogisticsPartner(),transferwarehouseEntity.getLogisticsPartner());
        assertEquals(transferwarehouse.getLogisticsPartnerUrl(),transferwarehouseEntity.getLogisticsPartnerUrl());
        assertEquals(transferwarehouse.getRegionGeoJson(),transferwarehouseEntity.getRegionGeoJson());
    }

    @Test
    public void entityToDto(){
        TransferwarehouseEntity transferwarehouseEntity = TransferwarehouseEntity.builder()
                .logisticsPartner("A").logisticsPartnerUrl("A").regionGeoJson("A").build();

        Transferwarehouse transferwarehouse = TransferwarehouseMapper.INSTANCE.entityToDto(transferwarehouseEntity);

        assertEquals(transferwarehouse.getLogisticsPartner(),transferwarehouseEntity.getLogisticsPartner());
        assertEquals(transferwarehouse.getLogisticsPartnerUrl(),transferwarehouseEntity.getLogisticsPartnerUrl());
        assertEquals(transferwarehouse.getRegionGeoJson(),transferwarehouseEntity.getRegionGeoJson());
    }
}