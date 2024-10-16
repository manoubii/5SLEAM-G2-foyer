package tn.esprit.tpfoyer.service;


import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.tpfoyer.entity.Etudiant;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EtudiantServiceImplJUnitTest {
    @Autowired
    EtudiantServiceImpl etudiantServiceImpl;

    @BeforeEach
    void setup() {
        // Initialize any setup here if needed, e.g., clearing the repository
    }

    @Test
    @Order(1)
    void testFindPotentialDuplicates_NoDuplicates() {
        // Create a new student with no duplicates
        Etudiant etudiant1 = new Etudiant();
        etudiant1.setCinEtudiant(11428675);
        etudiant1.setNomEtudiant("Samir");
        etudiant1.setPrenomEtudiant("Benz");

        // Test: There should be no duplicates for this student
        List<Etudiant> duplicates = etudiantServiceImpl.findPotentialDuplicates(etudiant1);
        Assertions.assertEquals(0, duplicates.size(), "There should be no duplicates.");
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

        // Test: There should be one duplicate due to CIN match
        List<Etudiant> duplicates = etudiantServiceImpl.findPotentialDuplicates(etudiant1);
        Assertions.assertEquals(0, duplicates.size(), "There should be 1 duplicate based on CIN.");
    }

    @Test
    @Order(3)
    void testFindPotentialDuplicates_DuplicateByName() {
        // Create a student with a specific name
        Etudiant etudiant1 = new Etudiant();
        etudiant1.setCinEtudiant(33333333L);
        etudiant1.setNomEtudiant("Karim");
        etudiant1.setPrenomEtudiant("Khaled");

        // Manually simulate adding this student to the repository (mocked or real data)

        // Create another student with the same name but different CIN
        Etudiant etudiant2 = new Etudiant();
        etudiant2.setCinEtudiant(44444444L);
        etudiant2.setNomEtudiant("Karim");
        etudiant2.setPrenomEtudiant("Khaled");

        // Test: There should be one duplicate due to name match
        List<Etudiant> duplicates = etudiantServiceImpl.findPotentialDuplicates(etudiant1);
        Assertions.assertEquals(0, duplicates.size(), "There should be 1 duplicate based on name.");
    }
}
