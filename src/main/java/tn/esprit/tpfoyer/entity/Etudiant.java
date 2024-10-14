package tn.esprit.tpfoyer.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Table(name = "etudiant")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Etudiant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idEtudiant;

    String nomEtudiant;
    String prenomEtudiant;
    long cinEtudiant;



    // Ajout de l'email
    @Column(unique = true) // Pour garantir l'unicité
             private String email;

    // Ajout de scoreExamen si nécessaire
    double scoreExamen;

    @ManyToMany(mappedBy = "etudiants")
    Set<Reservation> reservations;
}
