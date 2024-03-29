package at.fhtw.swen3.controller.rest;

import at.fhtw.swen3.controller.WarehouseApi;


import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.services.WarehouseService;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.Truck;
import at.fhtw.swen3.services.dto.Warehouse;
import at.fhtw.swen3.services.impl.WarehouseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-11-24T10:56:43.233247Z[Etc/UTC]")
@Controller
@Slf4j
public class WarehouseApiController implements WarehouseApi {

    private final NativeWebRequest request;

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    public WarehouseApiController(NativeWebRequest request) {
        log.info("constructor");
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        log.info("getRequest");
        log.info("testing yaml files");
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Warehouse> exportWarehouses() {
        log.info("exportWarehouses");
        //TODO fix it
        Warehouse warehouse = warehouseService.exportWarehouse();
        return new ResponseEntity<Warehouse>(warehouse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Hop> getWarehouse(String code) {
        log.info("getWarehouse " + code);
        Hop hop = warehouseService.getWarehouseOrTruckByCode(code);
        return new ResponseEntity<Hop>(hop, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<Void> importWarehouses(Warehouse warehouse) {
        log.info("importWarehouses ");
        warehouseService.importWarehouse(warehouse);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
