package at.fhtw.swen3.persistence.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "hoparrival")
public class HopArrivalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Pattern(regexp = "^[A-Z]{4}\\d{1,4}$")
    private String code = null;
    private String description = null;
    private OffsetDateTime dateTime = null;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parcel_id")
    private ParcelEntity parcelEntity;

}
