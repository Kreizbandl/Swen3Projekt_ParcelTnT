package at.fhtw.swen3.persistence.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransfarewarehouseEntity {
    private Long id;
    private String regionGeoJson = null;
    private String logisticsPartner = null;
    private String logisticsPartnerUrl = null;
}
