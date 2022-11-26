package at.fhtw.swen3.persistence.entities;

import lombok.*;

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
public class ParcelEntity {
    private Long id;
    @Min(value = 0, message = "Weight must be greater than 0")
    private Float weight = null;
    @NotNull
    private RecipientEntity recipient = null;
    @NotNull
    private RecipientEntity sender = null;
    @Pattern(regexp="^[A-Z0-9]{9}$")
    private String trackingId = null;
    private StateEnum state = null;
    @NotNull
    private List<HopArrivalEntity> visitedHops = new ArrayList<>();
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
}
