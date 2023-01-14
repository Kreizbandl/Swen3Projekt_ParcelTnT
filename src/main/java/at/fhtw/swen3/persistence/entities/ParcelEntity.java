package at.fhtw.swen3.persistence.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "parcel")
public class ParcelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Min(value = 0, message = "Weight must be greater than 0")
    private Float weight = null;
    @ManyToOne
    @JoinColumn(name = "recipient_id")
    @NotNull
    private RecipientEntity recipient = null;
    @ManyToOne
    @JoinColumn(name = "sender_id")
    @NotNull
    private RecipientEntity sender = null;
    @Pattern(regexp="^[A-Z0-9]{9}$")
    private String trackingId = null;
    private StateEnum state = null;
    @OneToMany(mappedBy = "parcelEntity", cascade = CascadeType.ALL)
    @NotNull
    private List<HopArrivalEntity> visitedHops = new ArrayList<>();
    @OneToMany(mappedBy = "parcelEntity", cascade = CascadeType.ALL)
    @NotNull
    private List<HopArrivalEntity> futureHops = new ArrayList<>();

    public enum StateEnum {
        PICKUP("Pickup"),

        INTRANSPORT("InTransport"),

        INTRUCKDELIVERY("InTruckDelivery"),

        TRANSFERRED("Transferred"),

        DELIVERED("Delivered");

        private String value;

        StateEnum(String value) {
            this.value = value;
        }
    }

    public void removeHopFromFutureHops(HopArrivalEntity hopArrivalEntity){
        this.futureHops.remove(hopArrivalEntity);
    }

    public void addHopToVisitedHops(HopArrivalEntity hopArrivalEntity){
        this.visitedHops.add(hopArrivalEntity);
    }
}
