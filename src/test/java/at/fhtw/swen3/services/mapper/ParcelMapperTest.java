package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.services.dto.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ParcelMapperTest {

    @Test
    public void multipleDtosToEntity() {
        Recipient recipient = new Recipient();
        recipient.setName("some name...");
        recipient.setStreet("some street...");
        recipient.setPostalCode("some code...");
        recipient.setCity("some city...");
        recipient.setCountry("some country...");
        Parcel parcel = new Parcel();
        parcel.setWeight(12.34f);
        parcel.setRecipient(recipient);
        parcel.setSender(recipient);

        NewParcelInfo newParcelInfo = new NewParcelInfo("ABCDEFGHI");
        newParcelInfo.setTrackingId("some tracking id...");

        OffsetDateTime time = OffsetDateTime.now();
        HopArrival hopArrival = new HopArrival();
        hopArrival.setCode("some code...");
        hopArrival.setDescription("some description...");
        hopArrival.setDateTime(time);
        List<HopArrival> visitedHopsDto = new ArrayList<HopArrival>();
        visitedHopsDto.add(hopArrival);

        TrackingInformation trackingInformation = new TrackingInformation();
        trackingInformation.setState(TrackingInformation.StateEnum.INTRANSPORT);
        trackingInformation.setVisitedHops(visitedHopsDto);
        trackingInformation.setFutureHops(visitedHopsDto);

        ParcelEntity parcelEntity = ParcelMapper.INSTANCE.dtoToEntity(parcel, newParcelInfo, trackingInformation);

        assertEquals("12.34",parcelEntity.getWeight().toString());
        assertEquals("some name...",parcelEntity.getRecipient().getName());
        assertEquals("some street...",parcelEntity.getRecipient().getStreet());
        assertEquals("some code...",parcelEntity.getRecipient().getPostalCode());
        assertEquals("some city...",parcelEntity.getRecipient().getCity());
        assertEquals("some country...",parcelEntity.getRecipient().getCountry());
        assertEquals("some name...",parcelEntity.getSender().getName());
        assertEquals("some street...",parcelEntity.getSender().getStreet());
        assertEquals("some code...",parcelEntity.getSender().getPostalCode());
        assertEquals("some city...",parcelEntity.getSender().getCity());
        assertEquals("some country...",parcelEntity.getSender().getCountry());
        assertEquals("some tracking id...",parcelEntity.getTrackingId());
        assertEquals("INTRANSPORT",parcelEntity.getState().toString());
        assertEquals("some code...",parcelEntity.getVisitedHops().get(0).getCode());
        assertEquals("some description...",parcelEntity.getVisitedHops().get(0).getDescription());
        assertEquals(time,parcelEntity.getVisitedHops().get(0).getDateTime());
        assertEquals("some code...",parcelEntity.getFutureHops().get(0).getCode());
        assertEquals("some description...",parcelEntity.getFutureHops().get(0).getDescription());
        assertEquals(time,parcelEntity.getFutureHops().get(0).getDateTime());

    }
    @Test
    public void entityToParcelDto(){
        RecipientEntity recipient = new RecipientEntity();
        recipient.setName("some name...");
        recipient.setStreet("some street...");
        recipient.setPostalCode("some code...");
        recipient.setCity("some city...");
        recipient.setCountry("some country...");

        OffsetDateTime time = OffsetDateTime.now();
        HopArrivalEntity hopArrival = new HopArrivalEntity();
        hopArrival.setCode("some code...");
        hopArrival.setDescription("some description...");
        hopArrival.setDateTime(time);
        List<HopArrivalEntity> visitedHops = new ArrayList<HopArrivalEntity>();
        visitedHops.add(hopArrival);

        ParcelEntity parcelEntity = new ParcelEntity();
        parcelEntity.setWeight(12.34f);
        parcelEntity.setRecipient(recipient);
        parcelEntity.setSender(recipient);
        parcelEntity.setTrackingId("some tracking id...");
        parcelEntity.setState(ParcelEntity.StateEnum.INTRANSPORT);
        parcelEntity.setVisitedHops(visitedHops);
        parcelEntity.setFutureHops(visitedHops);

        Parcel parcel = ParcelMapper.INSTANCE.entityToParcelDto(parcelEntity);

        assertEquals( "12.34", parcel.getWeight().toString());
        assertEquals( "some name...", parcel.getRecipient().getName());
        assertEquals( "some street...", parcel.getRecipient().getStreet());
        assertEquals( "some code...", parcel.getRecipient().getPostalCode());
        assertEquals( "some city...", parcel.getRecipient().getCity());
        assertEquals( "some country...", parcel.getRecipient().getCountry());
        assertEquals( "some name...", parcel.getSender().getName());
        assertEquals( "some street...", parcel.getSender().getStreet());
        assertEquals( "some code...", parcel.getSender().getPostalCode());
        assertEquals( "some city...", parcel.getSender().getCity());
        assertEquals( "some country...", parcel.getSender().getCountry());
    }
    @Test
    public void entityToNewParcelInfoDto(){
        RecipientEntity recipient = new RecipientEntity();
        recipient.setName("some name...");
        recipient.setStreet("some street...");
        recipient.setPostalCode("some code...");
        recipient.setCity("some city...");
        recipient.setCountry("some country...");

        OffsetDateTime time = OffsetDateTime.now();
        HopArrivalEntity hopArrival = new HopArrivalEntity();
        hopArrival.setCode("some code...");
        hopArrival.setDescription("some description...");
        hopArrival.setDateTime(time);
        List<HopArrivalEntity> visitedHops = new ArrayList<HopArrivalEntity>();
        visitedHops.add(hopArrival);

        ParcelEntity parcelEntity = new ParcelEntity();
        parcelEntity.setWeight(12.34f);
        parcelEntity.setRecipient(recipient);
        parcelEntity.setSender(recipient);
        parcelEntity.setTrackingId("some tracking id...");
        parcelEntity.setState(ParcelEntity.StateEnum.INTRANSPORT);
        parcelEntity.setVisitedHops(visitedHops);
        parcelEntity.setFutureHops(visitedHops);

        NewParcelInfo newParcelInfo = ParcelMapper.INSTANCE.entityToNewParcelInfoDto(parcelEntity);

        assertEquals( "some tracking id...", newParcelInfo.getTrackingId());
    }
    @Test
    public void entityToTrackingInformationDto(){
        RecipientEntity recipient = new RecipientEntity();
        recipient.setName("some name...");
        recipient.setStreet("some street...");
        recipient.setPostalCode("some code...");
        recipient.setCity("some city...");
        recipient.setCountry("some country...");

        OffsetDateTime time = OffsetDateTime.now();
        HopArrivalEntity hopArrival = new HopArrivalEntity();
        hopArrival.setCode("some code...");
        hopArrival.setDescription("some description...");
        hopArrival.setDateTime(time);
        List<HopArrivalEntity> visitedHops = new ArrayList<HopArrivalEntity>();
        visitedHops.add(hopArrival);

        ParcelEntity parcelEntity = new ParcelEntity();
        parcelEntity.setWeight(12.34f);
        parcelEntity.setRecipient(recipient);
        parcelEntity.setSender(recipient);
        parcelEntity.setTrackingId("some tracking id...");
        parcelEntity.setState(ParcelEntity.StateEnum.INTRANSPORT);
        parcelEntity.setVisitedHops(visitedHops);
        parcelEntity.setFutureHops(visitedHops);

        TrackingInformation trackingInformation = ParcelMapper.INSTANCE.entityToTrackingInformationDto(parcelEntity);

        assertEquals( "InTransport", trackingInformation.getState().toString());
        assertEquals("some code...", trackingInformation.getVisitedHops().get(0).getCode());
        assertEquals("some description...", trackingInformation.getVisitedHops().get(0).getDescription());
        assertEquals(time, trackingInformation.getVisitedHops().get(0).getDateTime());
        assertEquals("some code...", trackingInformation.getFutureHops().get(0).getCode());
        assertEquals("some description...", trackingInformation.getFutureHops().get(0).getDescription());
        assertEquals(time, trackingInformation.getFutureHops().get(0).getDateTime());
    }
}