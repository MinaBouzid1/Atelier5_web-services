package ma.fstt.gestion_stations.controllers;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import ma.fstt.gestion_stations.entities.Station;
import ma.fstt.gestion_stations.services.StationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stations")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class StationController {

    private final StationService stationService;

    // CREATE
    @PostMapping
    public ResponseEntity<Station> createStation(@RequestBody Station station) {
        try {
            Station createdStation = stationService.createStation(station);
            return new ResponseEntity<>(createdStation, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<Station>> getAllStations() {
        try {
            List<Station> stations = stationService.getAllStations();
            if (stations.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return new ResponseEntity<>(stations, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Station> getStationById(@PathVariable("id") Long id) {
        return stationService.getStationById(id)
                .map(station -> new ResponseEntity<>(station, HttpStatus.OK))
                .orElse(ResponseEntity.notFound().build());
    }

    // SEARCH BY NOM
    @GetMapping("/search/nom")
    public ResponseEntity<List<Station>> searchStationsByNom(@RequestParam String nom) {
        try {
            List<Station> stations = stationService.searchStationsByNom(nom);
            return new ResponseEntity<>(stations, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // SEARCH BY ADRESSE
    @GetMapping("/search/adresse")
    public ResponseEntity<List<Station>> searchStationsByAdresse(@RequestParam String adresse) {
        try {
            List<Station> stations = stationService.searchStationsByAdresse(adresse);
            return new ResponseEntity<>(stations, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Station> updateStation(@PathVariable("id") Long id, @RequestBody Station station) {
        try {
            Station updatedStation = stationService.updateStation(id, station);
            return new ResponseEntity<>(updatedStation, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteStation(@PathVariable("id") Long id) {
        try {
            stationService.deleteStation(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}