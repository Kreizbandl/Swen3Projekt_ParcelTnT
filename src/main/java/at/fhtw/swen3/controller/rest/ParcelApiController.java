package at.fhtw.swen3.controller.rest;

import at.fhtw.swen3.controller.ParcelApi;


import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.TrackingInformation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;
import javax.annotation.Generated;
import javax.sound.midi.Track;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-11-24T10:56:43.233247Z[Etc/UTC]")
@Controller
@Slf4j
public class ParcelApiController implements ParcelApi {

    private final NativeWebRequest request;

    @Autowired
    private ParcelService parcelService;

    @Autowired
    public ParcelApiController(NativeWebRequest request) {
        log.info("contrsuctor");
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        log.info("getRequest");
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Void> reportParcelDelivery(String trackingId) {
        log.info("reportParcelDelivery " + trackingId);
        return ParcelApi.super.reportParcelDelivery(trackingId);
    }

    @Override
    public ResponseEntity<Void> reportParcelHop(String trackingId, String code) {
        log.info("reportParcelHop " + trackingId + " " + code);
        return ParcelApi.super.reportParcelHop(trackingId, code);
    }

    @Override
    public ResponseEntity<NewParcelInfo> submitParcel(Parcel parcel) {
        log.info("submitParcel " + parcel);
        NewParcelInfo parcelInfo = parcelService.submitParcel(parcel);
        return new ResponseEntity<NewParcelInfo>(parcelInfo, HttpStatus.CREATED);
        //return ParcelApi.super.submitParcel(parcel);
    }

    @Override
    public ResponseEntity<TrackingInformation> trackParcel(String trackingId) {
        log.info("trackParcel " + trackingId);

        TrackingInformation trackingInformation = parcelService.trackParcel(trackingId);
        return new ResponseEntity<TrackingInformation>(trackingInformation, HttpStatus.FOUND);
        //return ParcelApi.super.trackParcel(trackingId);
    }

    @Override
    public ResponseEntity<NewParcelInfo> transitionParcel(String trackingId, Parcel parcel) {
        log.info("transitionParcel " + trackingId + " " + parcel);
        return ParcelApi.super.transitionParcel(trackingId, parcel);
    }
}
