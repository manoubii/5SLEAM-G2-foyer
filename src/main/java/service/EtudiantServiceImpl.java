package service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.repository.EtudiantRepository;

import java.util.List;

@Service // Ajoutez cette annotation pour indiquer que c'est un service
@AllArgsConstructor
public class EtudiantServiceImpl implements IEtudiantService {

    private final EtudiantRepository etudiantRepository;
    private final EmailService emailService; // Injection de EmailService

    @Override
    public List<Etudiant> retrieveAllEtudiants() {
        return etudiantRepository.findAll();
    }

    @Override
    public Etudiant retrieveEtudiant(Long etudiantId) {
        return etudiantRepository.findById(etudiantId).orElse(null);
    }

    @Override
    public Etudiant addEtudiant(Etudiant c) {
        return etudiantRepository.save(c);
    }

    @Override
    public Etudiant modifyEtudiant(Etudiant c) {
        return etudiantRepository.save(c);
    }

    @Override
    public void removeEtudiant(Long etudiantId) {
        etudiantRepository.deleteById(etudiantId);
    }

    @Override
    public Etudiant recupererEtudiantParCin(long cin) {
        return etudiantRepository.findEtudiantByCinEtudiant(cin);
    }

    @Override
    public Etudiant updateEtudiant(Long id, Etudiant etudiant) {
        // Ajouter une implémentation pour mettre à jour un étudiant
        return null;
    }

    @Override
    public void deleteEtudiant(Long etudiantId) {
        etudiantRepository.deleteById(etudiantId); // Implémentation de la méthode
    }

    public String inscrireEtudiant(Etudiant etudiant) {
        if (etudiant.getScoreExamen() < 70) {
            throw new IllegalArgumentException("L'étudiant n'a pas le score minimum requis.");
        }

        if (etudiantRepository.existsByEmail(etudiant.getEmail())) {
            throw new IllegalStateException("L'étudiant est déjà inscrit.");
        }

        etudiantRepository.save(etudiant);
        emailService.sendEmail(etudiant.getEmail(), "Bienvenue", "Bienvenue à l'école !"); // Utilisation de la méthode sendEmail
        return "Étudiant inscrit avec succès et email envoyé.";
    }

    public void notifyEtudiant(String email) {
        String subject = "Notification";
        String body = "Bonjour, ceci est une notification.";
        emailService.sendEmail(email, subject, body);
    }
}
