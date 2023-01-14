package at.fhtw.swen3.services;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.services.dto.*;

import java.util.Optional;

public interface ParcelService {
    public NewParcelInfo submitParcel(Parcel parcel);

    public void reportParcelDelivery(ParcelEntity parcel);

    public TrackingInformation trackParcel(String trackingId);

    //public GeoCoordinateEntity trackParcel(ParcelEntity parcel);

    public void reportParcelArrivalAtHop(ParcelEntity parcel, HopArrivalEntity hopArrival);

}
