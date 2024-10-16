package tn.esprit.tpfoyer.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.tpfoyer.entity.Etudiant;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class EtudiantServiceImplJUnitTest {

    @Autowired
    private EtudiantServiceImpl etudiantServiceImpl;

    @BeforeEach
    void setup() {
        // Initialize any setup here if needed, e.g., clearing the repository
    }

    @Test
    @Order(1)
     void testFindPotentialDuplicates_NoDuplicates() {
        // Create a new student with no duplicates
        Etudiant etudiant = new Etudiant();
        etudiant.setCinEtudiant(11428675);
        etudiant.setNomEtudiant("Lahbib");
        etudiant.setPrenomEtudiant("Manoubi");

        // Test: There should be no duplicates for this student
        List<Etudiant> duplicates = etudiantServiceImpl.findPotentialDuplicates(etudiant);
        Assertions.assertEquals(0, duplicates.size(), "There should be no duplicates.");
    }

    @Test
    @Order(2)
    void testFindPotentialDuplicates_DuplicateByCin() {
        // Create a student with a specific CIN
        Etudiant etudiant1 = new Etudiant();
        etudiant1.setCinEtudiant(11467509);
        etudiant1.setNomEtudiant("Sboui");
        etudiant1.setPrenomEtudiant("Louay");

        // Manually simulate adding this student to the repository (mocked or real data)

        // Create another student with the same CIN
        Etudiant etudiant2 = new Etudiant();
        etudiant2.setCinEtudiant(11467509);
        etudiant2.setNomEtudiant("Lahbib");
        etudiant2.setPrenomEtudiant("Ahmed");

        // Test: There should be one duplicate due to CIN match
        List<Etudiant> duplicates = etudiantServiceImpl.findPotentialDuplicates(etudiant1);
        Assertions.assertEquals(1, duplicates.size(), "There should be 1 duplicate based on CIN.");
    }

    @Test
    @Order(3)
    void testFindPotentialDuplicates_DuplicateByName() {
        // Create a student with a specific name
        Etudiant etudiant1 = new Etudiant();
        etudiant1.setCinEtudiant(11420030);
        etudiant1.setNomEtudiant("Sboui");
        etudiant1.setPrenomEtudiant("Khaled");

        // Manually simulate adding this student to the repository (mocked or real data)

        // Create another student with the same name but different CIN
        Etudiant etudiant2 = new Etudiant();
        etudiant2.setCinEtudiant(11429057);
        etudiant2.setNomEtudiant("Sboui");
        etudiant2.setPrenomEtudiant("Khaled");

        // Test: There should be one duplicate due to name match
        List<Etudiant> duplicates = etudiantServiceImpl.findPotentialDuplicates(etudiant1);
        Assertions.assertEquals(1, duplicates.size(), "There should be 1 duplicate based on name.");

    }
}
