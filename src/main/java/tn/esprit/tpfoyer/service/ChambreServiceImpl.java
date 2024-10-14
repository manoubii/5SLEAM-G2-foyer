package tn.esprit.tpfoyer.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;
import tn.esprit.tpfoyer.repository.ChambreRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ChambreServiceImpl implements IChambreService {

    private final ChambreRepository chambreRepository;
    private final NotificationService notificationService; // Service pour gérer les notifications

    public List<Chambre> retrieveAllChambres() {
        return chambreRepository.findAll();
    }

    public Chambre retrieveChambre(Long chambreId) {
        return chambreRepository.findById(chambreId).orElse(null);
    }

    public Chambre addChambre(Chambre chambre) {
        // Logique pour ajouter une chambre
        return chambreRepository.save(chambre);
    }

    public Chambre modifyChambre(Chambre chambre) {
        return chambreRepository.save(chambre);
    }

    @Override
    public Chambre trouverchambreSelonEtudiant(long Cin) {
        // Logique future
        return null;
    }

    @Override
    public List<Chambre> recupererChambresSelonTyp(TypeChambre tc) {
        // Logique future
        return null;
    }

    public void removeChambre(Long chambreId) {
        chambreRepository.deleteById(chambreId);
    }

    public String reserverChambre(Long chambreId) {
        // Récupérer la chambre à réserver
        Chambre chambre = chambreRepository.findById(chambreId)
                .orElseThrow(() -> new IllegalArgumentException("Chambre non trouvée avec l'ID: " + chambreId));

        // Vérifier si la chambre est déjà réservée
        if (chambre.isReservee()) {
            throw new IllegalArgumentException("La chambre est déjà réservée.");
        }

        // Réserver la chambre
        chambre.setReservee(true);
        chambreRepository.save(chambre);

        // Notifier l'utilisateur
        notificationService.envoyerNotification(chambre);

        return "Chambre réservée avec succès.";
    }
}
