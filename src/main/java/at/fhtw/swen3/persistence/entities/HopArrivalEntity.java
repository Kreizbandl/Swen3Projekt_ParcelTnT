package at.fhtw.swen3.persistence.entities;

import lombok.*;

import javax.validation.constraints.Pattern;
import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HopArrivalEntity {
    private Long id;
    @Pattern(regexp = "^[A-Z]{4}\\d{1,4}$")
    private String code = null;
    private String description = null;
    private OffsetDateTime dateTime = null;
}
