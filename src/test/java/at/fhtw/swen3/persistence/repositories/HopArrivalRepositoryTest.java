package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.OffsetDateTime;

@SpringBootTest
class HopArrivalRepositoryTest {

    @Autowired
    private HopArrivalRepository hopArrivalRepository;

    @Test
    public void repoTest(){
        /*HopArrivalEntity hopArrival = HopArrivalEntity.builder()
                .code("ABCD1")
                .description("...")
                .dateTime(OffsetDateTime.now())
                .parcelEntity(
                        new ParcelEntity()
                )
                .build();

        hopArrivalRepository.save(hopArrival);//TODO error vl wegen parcel nicht gefüllt?

        System.out.println(hopArrivalRepository.findAll());*/
    }

}