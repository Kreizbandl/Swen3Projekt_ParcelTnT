package at.fhtw.swen3.persistence.entities;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WarehouseEntity {
    private Long id;
    private Integer level = null;
    private List<WarehouseNextHopsEntity> nextHops = new ArrayList<>();

}
