package tn.esprit.tpfoyer.foyer.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.repository.FoyerRepository;
import tn.esprit.tpfoyer.service.FoyerServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class FoyerServiceImplTest {

    @Mock
    FoyerRepository foyerRepository;

    @InjectMocks
    FoyerServiceImpl foyerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSearchFoyers() {
        // Given
        Foyer f1 = new Foyer(1L, "Foyer 1", "Tunis", null, 100, "étudiant");
        Foyer f2 = new Foyer(2L, "Foyer 2", "Sousse", null, 200, "résidence");
        List<Foyer> expectedFoyers = Arrays.asList(f1, f2);

        // When
        when(foyerRepository.searchFoyers("Foyer 1", "Tunis")).thenReturn(expectedFoyers);

        // Execute the service method
        List<Foyer> actualFoyers = foyerService.searchFoyers("Foyer 1", "Tunis");

        // Then
        assertNotNull(actualFoyers);
        assertEquals(2, actualFoyers.size());
        assertEquals("Foyer 1", actualFoyers.get(0).getNomFoyer());

        // Verify the repository method was called
        verify(foyerRepository, times(1)).searchFoyers("Foyer 1", "Tunis");
    }

    @Test
    void testBulkUpdateFoyers() {
        // Given
        Foyer f1 = new Foyer(1L, "Foyer 1", "Tunis", null, 100, "étudiant");
        Foyer f2 = new Foyer(2L, "Foyer 2", "Sousse", null, 200, "résidence");
        List<Foyer> foyersToUpdate = Arrays.asList(f1, f2);

        // When
        when(foyerRepository.saveAll(foyersToUpdate)).thenReturn(foyersToUpdate);

        // Execute the service method
        List<Foyer> updatedFoyers = foyerService.bulkUpdateFoyers(foyersToUpdate);

        // Then
        assertNotNull(updatedFoyers);
        assertEquals(2, updatedFoyers.size());
        assertEquals("Foyer 1", updatedFoyers.get(0).getNomFoyer());
        assertEquals("Foyer 2", updatedFoyers.get(1).getNomFoyer());

        // Verify the repository method was called
        verify(foyerRepository, times(1)).saveAll(foyersToUpdate);
    }

    @Test
    void testDeleteAllFoyers() {
        // When
        foyerService.deleteAllFoyers();

        // Then
        verify(foyerRepository, times(1)).deleteAllFoyers(); // Vérifier que la méthode personnalisée est appelée
    }
}
