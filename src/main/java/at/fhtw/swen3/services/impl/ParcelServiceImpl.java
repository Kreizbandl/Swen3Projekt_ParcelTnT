package at.fhtw.swen3.services.impl;

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

import java.util.Optional;

@Slf4j
@AllArgsConstructor
public class ParcelServiceImpl implements ParcelService {

    @Autowired
    private ParcelRepository parcelRepository;
    @Autowired
    private RecipientRepository recipientRepository;

    private final Validator validator;

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

        NewParcelInfo newParcelInfo = new NewParcelInfo(trackingId);
        log.info("Submit parcel '" + parcel + "' with Tracking ID: " + trackingId);
        return newParcelInfo;
    }


    public void reportParcelDelivery(ParcelEntity parcel) {
        validator.validate(parcel);
        parcel.setState(ParcelEntity.StateEnum.DELIVERED);
        this.parcelRepository.save(parcel);
    }

}
