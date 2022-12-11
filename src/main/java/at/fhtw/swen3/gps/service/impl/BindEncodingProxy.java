package at.fhtw.swen3.gps.service.impl;

import at.fhtw.swen3.gps.service.GeoEncodingService;
import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BindEncodingProxy implements GeoEncodingService {

    private GeoCoordinateEntity parse(String responseBody){
        JSONArray json = new JSONArray(responseBody);//json-parser from json.org
        JSONObject obj = json.getJSONObject(0);

        return GeoCoordinateEntity.builder()
                .lat(Double.valueOf(obj.getString("lat")))
                .lon(Double.valueOf(obj.getString("lon")))
                .build();
    }

    @Override
    public GeoCoordinateEntity encodeAddress(RecipientEntity recipient) {
        HttpClient httpClient = HttpClient.newHttpClient();//forced me setting project level language to 11
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(
                        URI.create("https://nominatim.openstreetmap.org/search?" +
                                "street=" + recipient.getStreet() + //space between street and housenumber -> error
                                "&city=" + recipient.getCity() +
                                "&country=" + recipient.getCountry() +
                                "&postalcode=" + recipient.getPostalCode() +
                                "&format=json"))
                .build();

        return httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                //.thenAccept(System.out::println)
                .thenApply(this::parse)//call local method (lambda)
                .join();
    }
}
