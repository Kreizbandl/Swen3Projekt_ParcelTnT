package at.fhtw.swen3.persistence.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "transferwarehouse")
public class TransferwarehouseEntity extends HopEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition="text")
    @Lob
    private String regionGeoJson = null;
    private String logisticsPartner = null;
    private String logisticsPartnerUrl = null;

    @Override
    public String toString() {
        return "\nTransferwarehouseEntity{" +
                "id=" + id +
                ", regionGeoJson='" + regionGeoJson + '\'' +
                ", logisticsPartner='" + logisticsPartner + '\'' +
                ", logisticsPartnerUrl='" + logisticsPartnerUrl + '\'' +
                '}' + super.toString();
    }
}
