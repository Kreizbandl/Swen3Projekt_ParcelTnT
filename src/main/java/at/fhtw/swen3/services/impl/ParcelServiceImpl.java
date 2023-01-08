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
import at.fhtw.swen3.services.validation.Validator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public NewParcelInfo submitParcel(ParcelEntity parcel) {
        validator.validate(parcel);

        // TODO make ID unique
        // TODO add gps coordinates
        // generate tracking ID
        String trackingId = RandomStringUtils.randomAlphabetic(9).toUpperCase();

        parcel.setTrackingId(trackingId);
        parcel.setState(ParcelEntity.StateEnum.PICKUP);

        // write to DB
        this.parcelRepository.save(parcel);
        this.recipientRepository.save(parcel.getSender());
        this.recipientRepository.save(parcel.getRecipient());

        NewParcelInfo newParcelInfo = new NewParcelInfo();
        newParcelInfo.setTrackingId(trackingId);
        log.info("Submit parcel '" + parcel + "' with Tracking ID: " + trackingId);
        log.info("Coordinates for parcel: " + geoEncoding.encodeAddress(parcel.getRecipient()));
        log.info("Future hops: " + parcel.getFutureHops());
        return newParcelInfo;
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

    @Override
    public GeoCoordinateEntity trackParcel(ParcelEntity parcel) {
        validator.validate(parcel.getTrackingId());
        log.info("Visited hops for parcel: " + parcel.getVisitedHops());
        log.info("Future hops for parcel: " + parcel.getFutureHops());
        return geoEncoding.encodeAddress(parcel.getRecipient());
    }


}
