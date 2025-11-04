package ma.fstt.gestion_stations.controllers;

import ma.fstt.gestion_stations.entities.Carburant;
import ma.fstt.gestion_stations.services.CarburantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carburants")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class CarburantController {

    private final CarburantService carburantService;

    // CREATE
    @PostMapping
    public ResponseEntity<Carburant> createCarburant(@RequestBody Carburant carburant) {
        try {
            Carburant createdCarburant = carburantService.createCarburant(carburant);
            return new ResponseEntity<>(createdCarburant, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<Carburant>> getAllCarburants() {
        try {
            List<Carburant> carburants = carburantService.getAllCarburants();
            if (carburants.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return new ResponseEntity<>(carburants, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Carburant> getCarburantById(@PathVariable("id") Long id) {
        return carburantService.getCarburantById(id)
                .map(carburant -> new ResponseEntity<>(carburant, HttpStatus.OK))
                .orElse(ResponseEntity.notFound().build());
    }

    // READ BY TYPE
    @GetMapping("/type/{type}")
    public ResponseEntity<Carburant> getCarburantByType(@PathVariable("type") String type) {
        return carburantService.getCarburantByType(type)
                .map(carburant -> new ResponseEntity<>(carburant, HttpStatus.OK))
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Carburant> updateCarburant(@PathVariable("id") Long id, @RequestBody Carburant carburant) {
        try {
            Carburant updatedCarburant = carburantService.updateCarburant(id, carburant);
            return new ResponseEntity<>(updatedCarburant, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCarburant(@PathVariable("id") Long id) {
        try {
            carburantService.deleteCarburant(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}