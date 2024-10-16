package tn.esprit.tpfoyer.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.repository.EtudiantRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EtudiantServiceImpl implements IEtudiantService {


    EtudiantRepository etudiantRepository;

    public List<Etudiant> retrieveAllEtudiants() {
        return etudiantRepository.findAll();
    }
    public Etudiant retrieveEtudiant(Long etudiantId) {
        return etudiantRepository.findById(etudiantId).get();
    }
    public Etudiant addEtudiant(Etudiant c) {
        return etudiantRepository.save(c);
    }
    public Etudiant modifyEtudiant(Etudiant c) {
        return etudiantRepository.save(c);
    }
    public void removeEtudiant(Long etudiantId) {
        etudiantRepository.deleteById(etudiantId);
    }
    public Etudiant recupererEtudiantParCin(long cin)
    {
        return etudiantRepository.findEtudiantByCinEtudiant(cin);
    }

    // Find students with the same or similar CIN
    public List<Etudiant> findDuplicatesByCin(long cin) {
        return etudiantRepository.findAll().stream()
                .filter(etudiant -> etudiant.getCinEtudiant() == cin)
                .collect(Collectors.toList());
    }

    // Find students with similar names (same first and last names)
    public List<Etudiant> findDuplicatesByName(String nomEtudiant, String prenomEtudiant) {
        return etudiantRepository.findAll().stream()
                .filter(etudiant -> etudiant.getNomEtudiant().equalsIgnoreCase(nomEtudiant)
                        && etudiant.getPrenomEtudiant().equalsIgnoreCase(prenomEtudiant))
                .collect(Collectors.toList());
    }

    // Find potential duplicates (same CIN or similar names)
    public List<Etudiant> findPotentialDuplicates(Etudiant etudiant) {
        List<Etudiant> byCin = findDuplicatesByCin(etudiant.getCinEtudiant());
        List<Etudiant> byName = findDuplicatesByName(etudiant.getNomEtudiant(), etudiant.getPrenomEtudiant());

        // Combine the two lists to get potential duplicates
        return byCin.stream()
                .filter(e -> !e.equals(etudiant))
                .collect(Collectors.toList());
    }
}



