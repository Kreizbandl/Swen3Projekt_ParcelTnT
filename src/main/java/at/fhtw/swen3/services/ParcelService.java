package at.fhtw.swen3.services;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Recipient;

public interface ParcelService {
    public NewParcelInfo submitParcel(ParcelEntity parcel) throws Exception;

}
