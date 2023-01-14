package at.fhtw.swen3.persistence.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "warehouse")
public class WarehouseEntity extends HopEntity { //if error, this is new -> extends HopEntity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer level = null;
    @OneToMany (mappedBy = "hop", cascade = CascadeType.ALL) //references to "WarehouseNextHopsEntity -> HopEntity hop"
    @NotNull
    private List<WarehouseNextHopsEntity> nextHops = new ArrayList<>();

    @Override
    public String toString() {
        return "\nWarehouseEntity{" +
                "id=" + id +
                ", level=" + level +
                ", nextHops=" + nextHops +
                '}';//+ super.toString();
    }
}
