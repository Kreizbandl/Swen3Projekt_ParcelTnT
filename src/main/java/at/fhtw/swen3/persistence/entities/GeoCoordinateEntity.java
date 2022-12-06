package at.fhtw.swen3.persistence.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "geocoordinate")
public class GeoCoordinateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double lat = null;
    private Double lon = null;

    @Override
    public String toString() {
        return "GeoCoordinateEntity{" +
                "id=" + id +
                ", lat=" + lat +
                ", lon=" + lon +
                '}';
    }
}
