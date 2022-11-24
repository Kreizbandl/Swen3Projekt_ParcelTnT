package at.fhtw.swen3.persistence.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TruckEntity {
    private Long id;
    private String regionGeoJson = null;
    private String numberPlate = null;
}
