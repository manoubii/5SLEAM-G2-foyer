package tn.esprit.tpfoyer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.tpfoyer.entity.Foyer;

import java.util.List;

@Repository
public interface FoyerRepository extends JpaRepository<Foyer, Long> {

    // Méthode de recherche personnalisée avec correspondance partielle sur `nomFoyer` et `adresse`
    @Query("SELECT f FROM Foyer f WHERE f.nomFoyer LIKE %:nomFoyer% AND f.adresse LIKE %:adresse%")
    List<Foyer> searchFoyers(@Param("nomFoyer") String nomFoyer, @Param("adresse") String adresse);

    // Méthode personnalisée pour supprimer tous les foyers
    @Modifying
    @Transactional
    @Query("DELETE FROM Foyer")
    void deleteAllFoyers();
}
