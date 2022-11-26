package at.fhtw.swen3.persistence.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "transferwarehouse")
public class TransferwarehouseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String regionGeoJson = null;
    private String logisticsPartner = null;
    private String logisticsPartnerUrl = null;
}
