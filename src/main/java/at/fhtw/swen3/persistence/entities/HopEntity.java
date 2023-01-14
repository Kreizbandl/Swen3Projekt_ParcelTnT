package at.fhtw.swen3.persistence.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "hop")
public class HopEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String hopType = null;
    private String code = null;
    @Pattern(regexp = "[a-zA-ZÄÖÜäöüß0-9- ]+", message = "Warehouse-description must have only upper, lowercase letters, Numbers and -")
    private String description = null;
    private Integer processingDelayMins = null;
    private String locationName = null;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_coordinates_id")
    private GeoCoordinateEntity locationCoordinates = null;

    @Override
    public String toString() {
        return "\nHopEntity{" +
                "id=" + id +
                ", hopType='" + hopType + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", processingDelayMins=" + processingDelayMins +
                ", locationName='" + locationName + '\'' +
                ", locationCoordinates=" + locationCoordinates +
                '}';
    }
}
