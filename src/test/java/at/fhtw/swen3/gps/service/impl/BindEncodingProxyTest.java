package at.fhtw.swen3.gps.service.impl;

import at.fhtw.swen3.gps.service.GeoEncodingService;
import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import org.junit.jupiter.api.Test;

class BindEncodingProxyTest {
    @Test
    public void geoEncoderTest(){
        GeoEncodingService service = new BindEncodingProxy();

        GeoCoordinateEntity geoCoordinate = service.encodeAddress(
                RecipientEntity.builder()
                        .city("Gutenstein")
                        .street("Vorderbruck,73")//space between street and housenumber -> error
                        .postalCode("2770")
                        .country("Austria")
                        .name("Manuel")
                        .build()
        );

        System.out.println(geoCoordinate.toString());

    }
}