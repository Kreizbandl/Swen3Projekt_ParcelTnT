package at.fhtw.swen3.persistence.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecipientEntity {
    private Long id;
    private String name = null;
    private String street = null;
    private String postalCode = null;
    private String city = null;
    private String country = null;
}
