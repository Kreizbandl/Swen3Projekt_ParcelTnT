package at.fhtw.swen3.persistence.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GeoCoordinateEntity {
    private Long id;
    private Double lat = null;
    private Double lon = null;
}
