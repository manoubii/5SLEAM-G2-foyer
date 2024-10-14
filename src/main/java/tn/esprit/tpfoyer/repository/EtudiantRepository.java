package tn.esprit.tpfoyer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tpfoyer.entity.Etudiant;

import java.util.List;
import java.util.Optional;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {

    Etudiant findEtudiantByCinEtudiant(long cin);

    List<Etudiant> findAll();

    Optional findByIdEtudiant(Long etudiantId);
    boolean existsByEmail(String email);
}
