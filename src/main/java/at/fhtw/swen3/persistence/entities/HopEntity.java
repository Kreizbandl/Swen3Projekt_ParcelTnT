package at.fhtw.swen3.persistence.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HopEntity {
    private Long id;
    private String hopType = null;
    private String code = null;
    private String description = null;
    private Integer processingDelayMins = null;
    private String locationName = null;
    private GeoCoordinateEntity locationCoordinates = null;
}
