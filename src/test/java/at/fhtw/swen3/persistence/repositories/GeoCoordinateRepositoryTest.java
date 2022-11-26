package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GeoCoordinateRepositoryTest {

    @Autowired
    private GeoCoordinateRepository geoCoordinateRepository;

    @Test
    public void repoTest(){
        GeoCoordinateEntity geoCoordinate = GeoCoordinateEntity.builder()
                .lon(12.2)
                .lat(34.4)
                .build();

        geoCoordinateRepository.save(geoCoordinate);

        System.out.println(geoCoordinateRepository.findAll());
    }

}