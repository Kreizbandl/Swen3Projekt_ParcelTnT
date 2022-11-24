package at.fhtw.swen3.persistence.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WarehouseNextHopsEntity {
    private Long id;
    private Integer traveltimeMins = null;
    private HopEntity hop = null;
}
