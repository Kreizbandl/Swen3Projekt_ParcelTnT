package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.gps.service.GeoEncodingService;
import at.fhtw.swen3.gps.service.impl.BindEncodingProxy;
import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.persistence.repositories.RecipientRepository;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.TrackingInformation;
import at.fhtw.swen3.services.mapper.ParcelMapper;
import at.fhtw.swen3.services.validation.Validator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
@AllArgsConstructor
public class ParcelServiceImpl implements ParcelService {

    @Autowired
    private ParcelRepository parcelRepository;
    @Autowired
    private RecipientRepository recipientRepository;

    private final Validator validator;
    private final GeoEncodingService geoEncoding = new BindEncodingProxy();


    @Override
    public NewParcelInfo submitParcel(Parcel parcel) {
        ParcelEntity parcelEntity = ParcelMapper.INSTANCE.dtoToParcelEntity(parcel);

        //validate data
        validator.validate(parcel);

        // TODO make ID unique
        // generate tracking ID
        String trackingId = RandomStringUtils.randomAlphabetic(9).toUpperCase();
        parcelEntity.setTrackingId(trackingId);

        // TODO add gps coordinates

        //predict future hops
        //TODO kill me!!!
        List<HopArrivalEntity> list = new ArrayList<>();
        list.add(new HopArrivalEntity());
        parcelEntity.setFutureHops(list);
        parcelEntity.setVisitedHops(list);

        //set pickup state
        parcelEntity.setState(ParcelEntity.StateEnum.PICKUP);

        // write to DB
        this.recipientRepository.save(parcelEntity.getSender());
        this.recipientRepository.save(parcelEntity.getRecipient());
        this.parcelRepository.save(parcelEntity);

        NewParcelInfo newParcelInfo = new NewParcelInfo();
        newParcelInfo.setTrackingId(trackingId);
        log.info("Submit parcel '" + parcel + "' with Tracking ID: " + trackingId);
        //log.info("Coordinates for parcel: " + geoEncoding.encodeAddress(parcelEntity.getRecipient()));
        //log.info("Future hops: " + parcelEntity.getFutureHops());
        return newParcelInfo;
    }

    @Override
    public TrackingInformation trackParcel(String trackingId) {
        //validate tracking trackingId
        validator.validate(trackingId);//will this work?

        //fetch visited hops for parcel from db
        ParcelEntity parcelEntity = parcelRepository.findByTrackingId(trackingId);
        System.out.println("here we are: " + parcelEntity.toString());

        //predict or fetch future hops to final destination
        TrackingInformation trackingInformation = ParcelMapper.INSTANCE.entityToTrackingInformationDto(parcelEntity);

        return trackingInformation;
    }

    public void reportParcelDelivery(ParcelEntity parcel) {
        validator.validate(parcel);

        parcel.setState(ParcelEntity.StateEnum.DELIVERED);
        this.parcelRepository.save(parcel);
    }

    public void reportParcelArrivalAtHop(ParcelEntity parcel, HopArrivalEntity hopArrival) {
        validator.validate(parcel);
        validator.validate(hopArrival);

        log.info("Reporting Arrival of Parcel: " + parcel + "\n at hop: " + hopArrival);

        // remove hop from future hops and add to visited hops
        parcel.setFutureHops(parcel.getFutureHops().subList(1, parcel.getFutureHops().size()));

        List<HopArrivalEntity> visitedHops = parcel.getVisitedHops();
        visitedHops.add(0, hopArrival);
        parcel.setVisitedHops(visitedHops);

        // TODO update state depending on hop type

        this.parcelRepository.save(parcel);

    }

    /*@Override
    public GeoCoordinateEntity trackParcel(ParcelEntity parcel) {
        validator.validate(parcel.getTrackingId());
        log.info("Visited hops for parcel: " + parcel.getVisitedHops());
        log.info("Future hops for parcel: " + parcel.getFutureHops());
        return geoEncoding.encodeAddress(parcel.getRecipient());
    }*/


}
