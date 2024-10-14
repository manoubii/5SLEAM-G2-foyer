package tn.esprit.tpfoyer.control;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;
import tn.esprit.tpfoyer.service.IChambreService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/chambre")
public class ChambreRestController {

    private final IChambreService chambreService;

    // http://localhost:8089/tpfoyer/chambre/retrieve-all-chambres
    @GetMapping("/retrieve-all")
    public ResponseEntity<List<Chambre>> getChambres() {
        List<Chambre> listChambres = chambreService.retrieveAllChambres();
        return ResponseEntity.ok(listChambres);
    }

    @GetMapping("/retrieve/{chambreId}")
    public ResponseEntity<Chambre> retrieveChambre(@PathVariable("chambreId") Long chId) {
        Chambre chambre = chambreService.retrieveChambre(chId);
        return chambre != null
                ? ResponseEntity.ok(chambre)
                : ResponseEntity.notFound().build();
    }

    // http://localhost:8089/tpfoyer/chambre/add
    @PostMapping("/add")
    public ResponseEntity<Chambre> addChambre(@RequestBody Chambre c) {
        Chambre chambre = chambreService.addChambre(c);
        return ResponseEntity.status(201).body(chambre); // 201 Created
    }

    // http://localhost:8089/tpfoyer/chambre/remove/{chambreId}
    @DeleteMapping("/remove/{chambreId}")
    public ResponseEntity<Void> removeChambre(@PathVariable("chambreId") Long chId) {
        chambreService.removeChambre(chId);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    // http://localhost:8089/tpfoyer/chambre/modify
    @PutMapping("/modify")
    public ResponseEntity<Chambre> modifyChambre(@RequestBody Chambre c) {
        Chambre chambre = chambreService.modifyChambre(c);
        return chambre != null
                ? ResponseEntity.ok(chambre)
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/find-by-type/{typeChambre}")
    public ResponseEntity<List<Chambre>> trouverChambresSelonType(@PathVariable("typeChambre") TypeChambre typeChambre) {
        List<Chambre> chambres = chambreService.recupererChambresSelonTyp(typeChambre);
        return ResponseEntity.ok(chambres);
    }

    // http://localhost:8089/tpfoyer/chambre/find-by-etudiant/{cin}
    @GetMapping("/find-by-etudiant/{cin}")
    public ResponseEntity<Chambre> trouverChambreSelonEtudiant(@PathVariable("cin") long cin) {
        Chambre chambre = chambreService.trouverchambreSelonEtudiant(cin);
        return chambre != null
                ? ResponseEntity.ok(chambre)
                : ResponseEntity.notFound().build();
    }
}
