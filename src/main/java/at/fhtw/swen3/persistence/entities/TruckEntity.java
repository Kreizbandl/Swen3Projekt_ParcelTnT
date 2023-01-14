package at.fhtw.swen3.persistence.entities;

import at.fhtw.swen3.services.dto.Hop;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "truck")
public class TruckEntity extends HopEntity {// if error, this is new -> extends HopEntity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String regionGeoJson = null;
    private String numberPlate = null;

    @Override
    public String toString() {
        return "\nTruckEntity{" +
                "id=" + id +
                ", regionGeoJson='" + regionGeoJson + '\'' +
                ", numberPlate='" + numberPlate + '\'' +
                '}' + super.toString();
    }
}
