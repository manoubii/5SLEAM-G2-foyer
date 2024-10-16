package tn.esprit.tpfoyer.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.repository.EtudiantRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EtudiantServiceImplMockitoTest {

    @Mock
    EtudiantRepository etudiantRepository;

    @InjectMocks
    EtudiantServiceImpl etudiantService;

    private Etudiant etudiant1;
    private Etudiant etudiant2;
    private List<Etudiant> etudiantList;

    @BeforeEach
    void setUp() {
        etudiant1 = new Etudiant();
        etudiant1.setCinEtudiant(11428675);
        etudiant1.setNomEtudiant("Lahbib");
        etudiant1.setPrenomEtudiant("Manoubi");

        etudiant2 = new Etudiant();
        etudiant2.setCinEtudiant(11428675); // Duplicate CIN
        etudiant2.setNomEtudiant("Lahbib");
        etudiant2.setPrenomEtudiant("Manoubi");

        etudiantList = new ArrayList<>();
        etudiantList.add(etudiant1);
        etudiantList.add(etudiant2);
    }

    @Test
    void testFindDuplicatesByCin() {
        when(etudiantRepository.findAll()).thenReturn(etudiantList);

        List<Etudiant> result = etudiantService.findDuplicatesByCin(11428675);

        assertEquals(2, result.size()); // Both etudiant1 and etudiant2 have the same CIN
    }

    //@Test
    //void testFindDuplicatesByName() {
      //  when(etudiantRepository.findAll()).thenReturn(etudiantList);

        //List<Etudiant> result = etudiantService.findDuplicatesByName("Doe", "John");

        //assertEquals(1, result.size());
        //assertEquals("John", result.get(0).getNomEtudiant());
    //}

    @Test
    void testFindPotentialDuplicates() {
        when(etudiantRepository.findAll()).thenReturn(etudiantList);

        List<Etudiant> result = etudiantService.findPotentialDuplicates(etudiant1);

        assertEquals(1, result.size()); // Only etudiant2 should be found as a duplicate
        //assertEquals("Jane", result.get(0).getNomEtudiant());
    }
}
