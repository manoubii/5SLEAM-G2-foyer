package tn.esprit.tpfoyer.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.repository.ChambreRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ChambreServiceTest {

    @Mock
    private ChambreRepository chambreRepository;

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private ChambreServiceImpl chambreService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindChambreById() {
        // Données de test
        Chambre chambre = new Chambre();
        chambre.setIdChambre(1L);
        chambre.setNumeroChambre("101");
        chambre.setEstDisponible(true);
        chambre.setCapacite(2);

        // Simulation du comportement du repository
        when(chambreRepository.findById(1L)).thenReturn(Optional.of(chambre));

        // Appel de la méthode à tester
        Chambre foundChambre = chambreService.retrieveChambre(1L);

        // Vérifications
        assertEquals("101", foundChambre.getNumeroChambre());
    }

    @Test
    public void testReserverChambre() {
        Chambre chambre = new Chambre();
        chambre.setIdChambre(1L);
        chambre.setReservee(false);

        // Simulation du repository
        when(chambreRepository.findById(1L)).thenReturn(Optional.of(chambre));

        // Appel de la méthode à tester
        String result = chambreService.reserverChambre(1L);

        // Vérifications
        assertEquals("Chambre réservée avec succès.", result);
        verify(chambreRepository).save(chambre);
        verify(notificationService).envoyerNotification(chambre);
    }
}
