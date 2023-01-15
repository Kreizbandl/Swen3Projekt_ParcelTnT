package at.fhtw.swen3.services;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.services.dto.*;

import java.util.Optional;

public interface ParcelService {
    NewParcelInfo submitParcel(Parcel parcel);
    void reportParcelDelivery(String trackingId);
    TrackingInformation trackParcel(String trackingId);
    NewParcelInfo transitionParcel(String trackingId, Parcel parcel);
    void reportParcelArrivalAtHop(String trackingId, String code);

    //GeoCoordinateEntity trackParcel(ParcelEntity parcel);

    //void reportParcelArrivalAtHop(ParcelEntity parcel, HopArrivalEntity hopArrival);

}
