package tn.esprit.tpfoyer.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Foyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idFoyer;

    String nomFoyer;

    String adresse;

    @ManyToOne(cascade = CascadeType.ALL)
    Universite universite;

    int capacite; // Nouvel attribut pour la capacité du foyer

    String type; // Nouvel attribut pour le type de foyer (par exemple, étudiant, résidence)

}
