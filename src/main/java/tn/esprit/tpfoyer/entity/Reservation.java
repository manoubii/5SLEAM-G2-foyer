package tn.esprit.tpfoyer.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Reservation {

    @Id
    String idReservation;

    @Temporal(TemporalType.DATE)
    Date anneeUniversitaire;

    boolean estValide;

    @ManyToMany
    Set<Etudiant> etudiants;

    @Temporal(TemporalType.DATE)
    Date dateReservation;

    @Enumerated(EnumType.STRING)
    TypeReservation typeReservation;

    public enum TypeReservation {
        STANDARD,
        PREMIUM,
        VIP
    }
}
