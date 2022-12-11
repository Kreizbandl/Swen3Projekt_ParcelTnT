package at.fhtw.swen3.gps.service;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;

public interface GeoEncodingService {
    GeoCoordinateEntity encodeAddress(RecipientEntity recipient);
}
