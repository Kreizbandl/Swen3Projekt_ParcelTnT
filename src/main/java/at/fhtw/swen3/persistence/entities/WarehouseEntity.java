package at.fhtw.swen3.persistence.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "warehouse")
public class WarehouseEntity {
    //TODO WarehouseEntity extends HopEntity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer level = null;
    @OneToMany (mappedBy = "hop") //references to "WarehouseNextHopsEntity -> HopEntity hop"
    @NotNull
    private List<WarehouseNextHopsEntity> nextHops = new ArrayList<>();

}
