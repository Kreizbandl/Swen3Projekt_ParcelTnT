package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.TruckEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface HopRepository extends JpaRepository<HopEntity,Long> {

    @Transactional//to handle large data from table/table attribute (regionGeoJson)
    HopEntity getWarehouseOrTruckByCode(String code);
    //String getHopTypeByCode(String code);
    @Transactional
    List<TruckEntity> findAllByHopType(String hopType);
}
