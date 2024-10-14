package tn.esprit.tpfoyer.service;

import tn.esprit.tpfoyer.entity.Chambre;

public class NotificationService {
    public void envoyerNotification(Chambre chambre) {
        // Logique pour envoyer une notification à l'utilisateur
        System.out.println("Notification: La chambre " + chambre.getIdChambre() + " a été réservée.");
    }

    // Si vous n'avez pas besoin de cette méthode, vous pouvez la supprimer
    // public Long getIdChambre() {
    //     Long idChambre = null;
    //     return idChambre;
    // }
}
