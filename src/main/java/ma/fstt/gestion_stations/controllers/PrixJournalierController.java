package ma.fstt.gestion_stations.controllers;

import ma.fstt.gestion_stations.entities.PrixJournalier;
import ma.fstt.gestion_stations.services.PrixJournalierService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/prix")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class PrixJournalierController {

    private final PrixJournalierService prixJournalierService;

    // CREATE
    @PostMapping("/station/{stationId}/carburant/{carburantId}")
    public ResponseEntity<PrixJournalier> createPrixJournalier(
            @PathVariable Long stationId,
            @PathVariable Long carburantId,
            @RequestBody PrixJournalier prixJournalier) {
        try {
            PrixJournalier createdPrix = prixJournalierService.createPrixJournalier(stationId, carburantId, prixJournalier);
            return new ResponseEntity<>(createdPrix, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<PrixJournalier>> getAllPrixJournaliers() {
        try {
            List<PrixJournalier> prix = prixJournalierService.getAllPrixJournaliers();
            if (prix.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return new ResponseEntity<>(prix, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<PrixJournalier> getPrixJournalierById(@PathVariable("id") Long id) {
        return prixJournalierService.getPrixJournalierById(id)
                .map(prix -> new ResponseEntity<>(prix, HttpStatus.OK))
                .orElse(ResponseEntity.notFound().build());
    }

    // READ BY STATION
    @GetMapping("/station/{stationId}")
    public ResponseEntity<List<PrixJournalier>> getPrixByStation(@PathVariable Long stationId) {
        try {
            List<PrixJournalier> prix = prixJournalierService.getPrixByStation(stationId);
            return new ResponseEntity<>(prix, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // READ BY STATION AND DATE
    @GetMapping("/station/{stationId}/date/{date}")
    public ResponseEntity<List<PrixJournalier>> getPrixByStationAndDate(
            @PathVariable Long stationId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        try {
            List<PrixJournalier> prix = prixJournalierService.getPrixByStationAndDate(stationId, date);
            return new ResponseEntity<>(prix, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // READ BY CARBURANT
    @GetMapping("/carburant/{carburantId}")
    public ResponseEntity<List<PrixJournalier>> getPrixByCarburant(@PathVariable Long carburantId) {
        try {
            List<PrixJournalier> prix = prixJournalierService.getPrixByCarburant(carburantId);
            return new ResponseEntity<>(prix, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // READ HISTORIQUE
    @GetMapping("/historique/station/{stationId}/carburant/{carburantId}")
    public ResponseEntity<List<PrixJournalier>> getHistoriquePrix(
            @PathVariable Long stationId,
            @PathVariable Long carburantId) {
        try {
            List<PrixJournalier> prix = prixJournalierService.getHistoriquePrix(stationId, carburantId);
            return new ResponseEntity<>(prix, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<PrixJournalier> updatePrixJournalier(
            @PathVariable("id") Long id,
            @RequestBody PrixJournalier prixJournalier) {
        try {
            PrixJournalier updatedPrix = prixJournalierService.updatePrixJournalier(id, prixJournalier);
            return new ResponseEntity<>(updatedPrix, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePrixJournalier(@PathVariable("id") Long id) {
        try {
            prixJournalierService.deletePrixJournalier(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}