package tn.esprit.tpfoyer.control;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.service.IEtudiantService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/etudiant")
public class EtudiantRestController {

    private final IEtudiantService etudiantService;

    @GetMapping("/retrieve-all-etudiants")
    public List<Etudiant> getEtudiants() {
        try {
            return etudiantService.retrieveAllEtudiants();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur lors de la récupération des étudiants", e);
        }
    }

    @PostMapping("/add-etudiant")
    public Etudiant addEtudiant(@RequestBody Etudiant etudiant) {
        try {
            return etudiantService.addEtudiant(etudiant);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erreur lors de l'ajout de l'étudiant", e);
        }
    }

    @PutMapping("/update-etudiant/{id}")
    public Etudiant updateEtudiant(@PathVariable Long id, @RequestBody Etudiant etudiant) {
        try {
            return etudiantService.updateEtudiant(id, etudiant);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Étudiant non trouvé", e);
        }
    }

    @DeleteMapping("/delete-etudiant/{id}")
    public void deleteEtudiant(@PathVariable Long id) {
        try {
            etudiantService.deleteEtudiant(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Étudiant non trouvé", e);
        }
    }
}
