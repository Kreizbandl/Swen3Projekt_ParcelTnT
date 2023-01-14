package at.fhtw.swen3.persistence.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "warehousenexthops")
public class WarehouseNextHopsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer traveltimeMins = null;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hop_id")
    private HopEntity hop = null;

    /*@Override
    public String toString() {
        return "\nWarehouseNextHopsEntity{" +
                "id=" + id +
                ", traveltimeMins=" + traveltimeMins +
                ", hop=" + hop +
                '}';
    }*/
}
