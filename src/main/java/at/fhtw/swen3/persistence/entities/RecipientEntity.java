package at.fhtw.swen3.persistence.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "recipient")
public class RecipientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Pattern(regexp = "[a-zA-ZÄÖÜäöüß]+", message = "username must have only upper & lowercase letters")
    private String name = null;
    @Pattern(regexp = "[a-zA-Z0-9ÄÖÜäöüß / ]+", message = "streetname must have only upper, lowercase letters, Numbers and /")
    private String street = null;
    @Pattern(regexp = "[A0-9-]+", message = "username must have only upper & lowercase letters")
    private String postalCode = null;
    @Pattern(regexp = "[a-zA-ZÄÖÜäöüß]+", message = "cityname must have only upper & lowercase letters")
    private String city = null;
    @NotNull
    private String country = null;
}
