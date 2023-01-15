package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.repositories.HopRepository;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.persistence.repositories.RecipientRepository;
import at.fhtw.swen3.persistence.repositories.WarehouseRepository;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.Recipient;
import at.fhtw.swen3.services.dto.TrackingInformation;
import at.fhtw.swen3.services.mapper.ParcelMapper;
import at.fhtw.swen3.services.mapper.ParcelMapperImpl;
import at.fhtw.swen3.services.validation.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.event.annotation.BeforeTestExecution;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParcelServiceImplTest {

    static Parcel parcel;
    static ParcelService parcelService;
    @Mock
    private ParcelRepository parcelRepository;
    @Mock
    private RecipientRepository recipientRepository;
    @Mock
    private HopRepository hopRepository;
    @Mock
    private WarehouseRepository warehouseRepository;
    static Validator validator = new Validator();

    private String trackingid;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
        Recipient recipient = new Recipient( "Max", "Street 12", "1020", "Wien", "Österreich");
        Recipient sender = new Recipient("Martin", "Street 21", "1010", "Wien", "Österreich");
        List<HopArrivalEntity> hops = new ArrayList<>();
        hops.add(new HopArrivalEntity());

        parcel = new Parcel(12.2f, recipient, sender);

        parcelService = new ParcelServiceImpl(parcelRepository, recipientRepository, hopRepository, warehouseRepository, validator);
    }

    @Test
    void submitParcelTest() {
        NewParcelInfo newParcelInfo = parcelService.submitParcel(parcel);


        String trackingid = newParcelInfo.getTrackingId();
        validator.validate(trackingid);
        assertNotNull(trackingid);

    }

    @Test
    void trackParcelTest() {
        MockitoAnnotations.initMocks(this);
        Recipient recipient = new Recipient( "Max", "Street 12", "1020", "Wien", "Österreich");
        Recipient sender = new Recipient("Martin", "Street 21", "1010", "Wien", "Österreich");
        List<HopArrivalEntity> hops = new ArrayList<>();
        hops.add(new HopArrivalEntity());

        parcel = new Parcel(12.2f, recipient, sender);

        parcelService = new ParcelServiceImpl(parcelRepository, recipientRepository, hopRepository, warehouseRepository, validator);
        NewParcelInfo newParcelInfo = parcelService.submitParcel(parcel);
        String trackingid = newParcelInfo.getTrackingId();
        System.out.println(trackingid);
        TrackingInformation trackingInformation = parcelService.trackParcel(trackingid);

//        System.out.println(trackingInformation.getState());
        assertNotNull(trackingInformation);
    }

    @Test
    void transitionParcelTest() {
    }

    @Test
    void reportParcelArrivalAtHopTest() {
    }

    @Test
    void reportParcelDeliveryTest() {
        //ParcelMapper parcelMapper = new ParcelMapperImpl();
        NewParcelInfo newParcelInfo = parcelService.submitParcel(parcel);
        String trackingid = newParcelInfo.getTrackingId();
        parcelService.reportParcelDelivery(trackingid);

        ParcelEntity parcelEntity = ParcelMapper.INSTANCE.dtoToParcelEntity(parcel);
        System.out.println(parcelEntity.getState());

    }
}