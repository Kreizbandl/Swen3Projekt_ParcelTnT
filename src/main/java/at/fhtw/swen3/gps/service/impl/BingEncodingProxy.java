package at.fhtw.swen3.gps.service.impl;

import at.fhtw.swen3.gps.service.GeoEncodingService;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.services.dto.GeoCoordinate;

public class BingEncodingProxy implements GeoEncodingService {
    @Override
    public GeoCoordinate encodeAddress(RecipientEntity recipient) {
        return null;
    }
}
