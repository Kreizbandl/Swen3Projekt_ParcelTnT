package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.services.dto.HopArrival;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HopArrivalMapperTest {

    @Test
    public void dtoToEntity(){
        HopArrival hopArrival = new HopArrival();
        hopArrival.setCode("1234");
        hopArrival.setDateTime(OffsetDateTime.now());
        hopArrival.setDescription("some desc...");

        HopArrivalEntity hopArrivalEntity = HopArrivalMapper.INSTANCE.dtoToEntity(hopArrival);

        assertEquals(hopArrival.getCode(),hopArrivalEntity.getCode());
        assertEquals(hopArrival.getDateTime(),hopArrivalEntity.getDateTime());
        assertEquals(hopArrival.getDescription(),hopArrivalEntity.getDescription());
    }

    @Test
    public void entityToDto(){
        HopArrivalEntity hopArrivalEntity = HopArrivalEntity.builder().code("1234").dateTime(OffsetDateTime.now()).description("some desc...").build();

        HopArrival hopArrival = HopArrivalMapper.INSTANCE.entityToDto(hopArrivalEntity);

        assertEquals(hopArrival.getCode(),hopArrivalEntity.getCode());
        assertEquals(hopArrival.getDateTime(),hopArrivalEntity.getDateTime());
        assertEquals(hopArrival.getDescription(),hopArrivalEntity.getDescription());
    }
}