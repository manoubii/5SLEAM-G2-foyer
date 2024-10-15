package tn.esprit.tpfoyer.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "chambre")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Chambre {



    private Double prixParNuit;
    private boolean reservee;
    private Integer capacite; // Capacité d'accueil de la chambre
    private Integer colocatairesActuels;
    private boolean serviceRepasInclus;
    private boolean accesSalleSport;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChambre;

    private String numeroChambre;
    private boolean estDisponible;  // Assurez-vous que cette propriété existe


    @ManyToMany
    @JoinTable(
            name = "chambre_reservation",
            joinColumns = @JoinColumn(name = "chambre_id"),
            inverseJoinColumns = @JoinColumn(name = "reservation_id")
    )
    private Set<Reservation> reservations;

    // Ajoutez une méthode pour vérifier si la chambre est réservée
    public boolean isReservee() {
        return !estDisponible;  // Exemple d'implémentation
    }

    // Ajoutez un setter si nécessaire
    public void setReservee(boolean reservee) {
        this.estDisponible = !reservee;  // Exemple d'implémentation
    }
}
