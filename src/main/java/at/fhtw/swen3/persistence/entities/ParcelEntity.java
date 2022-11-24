package at.fhtw.swen3.persistence.entities;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParcelEntity {
    private Long id;
    private Float weight = null;
    private RecipientEntity recipient = null;
    private RecipientEntity sender = null;
    private String trackingId = null;
    private StateEnum state = null;
    private List<HopArrivalEntity> visitedHops = new ArrayList<>();
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
}
