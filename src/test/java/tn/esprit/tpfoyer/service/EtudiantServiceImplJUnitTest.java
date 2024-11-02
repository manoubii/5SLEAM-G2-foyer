package tn.esprit.tpfoyer.service;


import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.repository.EtudiantRepository;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EtudiantServiceImplJUnitTest {
    
    @Autowired
    EtudiantRepository etudiantRepository;
    private EtudiantServiceImpl etudiantServiceImpl;
    @BeforeEach
    void setup() {
        // Initialize the service with the real repository
       etudiantServiceImpl = new EtudiantServiceImpl(etudiantRepository);
    }

    @Test
    @Order(1)
    void testFindPotentialDuplicates_NoDuplicates() {
        // Create a new student with no duplicates
        Etudiant etudiant1 = new Etudiant();
        etudiant1.setCinEtudiant(11428675);
        etudiant1.setNomEtudiant("Samir");
        etudiant1.setPrenomEtudiant("Benz");

        Etudiant etudiant2 = new Etudiant();
        etudiant2.setCinEtudiant(11428675); // Duplicate CIN
        etudiant2.setNomEtudiant("Lahbib");
        etudiant2.setPrenomEtudiant("Manoubi");

        List<Etudiant>etudiantList = new ArrayList<>();
        etudiantList.add(etudiant1);
        etudiantList.add(etudiant2);

        // Test: There should be no duplicates for this student
        List<Etudiant> duplicates = etudiantServiceImpl.findPotentialDuplicates(etudiant1);
        Assertions.assertEquals(0, duplicates.size(), "There should be no duplicates.");
        System.out.println("Expected: " + 0+  ", Actual: " + duplicates.size());
    }

    @Test
    @Order(2)
    void testFindPotentialDuplicates_DuplicateByCin() {
        // Create a student with a specific CIN
        Etudiant etudiant1 = new Etudiant();
        etudiant1.setCinEtudiant(11428677);
        etudiant1.setNomEtudiant("Ahmed");
        etudiant1.setPrenomEtudiant("Ali");

        // Manually simulate adding this student to the repository (mocked or real data)

        // Create another student with the same CIN
        Etudiant etudiant2 = new Etudiant();
        etudiant2.setCinEtudiant(11428677);
        etudiant2.setNomEtudiant("Amine");
        etudiant2.setPrenomEtudiant("Ben");
        etudiantRepository.save(etudiant2);

        // Test: There should be one duplicate due to CIN match
        List<Etudiant> duplicates = etudiantServiceImpl.findPotentialDuplicates(etudiant1);
        Assertions.assertEquals(1, duplicates.size(), "There should be 1 duplicate based on CIN.");
        System.out.println("Expected: " + 1 +  ", Actual: " + duplicates.size());
    }

    @Test
    @Order(3)
    void testFindPotentialDuplicates_DuplicateByName() {
        // Create a student with a specific name
        Etudiant etudiant1 = new Etudiant();
        etudiant1.setCinEtudiant(33333333L);
        etudiant1.setNomEtudiant("Karim");
        etudiant1.setPrenomEtudiant("Khaled");
        etudiantRepository.save(etudiant1);

        // Manually simulate adding this student to the repository (mocked or real data)

        // Create another student with the same name but different CIN
        Etudiant etudiant2 = new Etudiant();
        etudiant2.setCinEtudiant(33333333L);
        etudiant2.setNomEtudiant("NotKhaled");
        etudiant2.setPrenomEtudiant("Khaled");
        etudiantRepository.save(etudiant2);
        
        // Test: There should be one duplicate due to name match
        List<Etudiant> duplicates = etudiantServiceImpl.findDuplicatesByName("Karim", "Khaled");
        Assertions.assertEquals(1, duplicates.size(), "There should be 1 duplicate based on name.");
        System.out.println("Expected: " + 1 +  ", Actual: " + duplicates.size());
    }
}
