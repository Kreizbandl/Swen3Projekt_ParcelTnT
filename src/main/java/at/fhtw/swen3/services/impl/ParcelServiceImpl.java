package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.persistence.repositories.RecipientRepository;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@AllArgsConstructor
public class ParcelServiceImpl implements ParcelService {

    @Autowired
    private ParcelRepository parcelRepository;
    @Autowired
    private RecipientRepository recipientRepository;

    @Override
    public NewParcelInfo submitParcel(ParcelEntity parcel) {
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

}
