package at.fhtw.swen3.persistence.entities;

import lombok.*;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HopEntity {
    private Long id;
    private String hopType = null;
    private String code = null;
    @Pattern(regexp = "[a-zA-ZÄÖÜäöüß0-9- ]+", message = "Warehouse-description must have only upper, lowercase letters, Numbers and -")
    private String description = null;
    private Integer processingDelayMins = null;
    private String locationName = null;
    private GeoCoordinateEntity locationCoordinates = null;
}
