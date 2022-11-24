package at.fhtw.swen3.persistence.entities;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HopArrivalEntity {
    private Long id;
    private String code = null;
    private String description = null;
    private OffsetDateTime dateTime = null;
}
