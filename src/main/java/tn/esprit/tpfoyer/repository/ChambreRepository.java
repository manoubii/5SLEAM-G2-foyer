package tn.esprit.tpfoyer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;

import java.util.List;

@Repository
public interface ChambreRepository extends JpaRepository<Chambre, Long> {

    // Requête pour trouver la chambre selon le CIN de l'étudiant qui l'occupe
    @Query("SELECT ch FROM Chambre ch INNER JOIN ch.reservations r INNER JOIN r.etudiants e WHERE e.cinEtudiant = :cin")
    Chambre trouverChselonEt(@Param("cin") long cin);

    // Trouver toutes les chambres selon leur type
    List<Chambre> findAllByTypeC(TypeChambre tc);

    // Trouver une chambre par son numéro
    Chambre findChambreByNumeroChambre(Long num);

    // Trouver toutes les chambres selon leur disponibilité
    List<Chambre> findAllByEstDisponible(boolean estDisponible);

    // Trouver toutes les chambres selon leur capacité
    List<Chambre> findAllByCapaciteGreaterThanEqual(int capacite);
}
