package ma.fstt.gestion_stations.services;

import ma.fstt.gestion_stations.entities.Carburant;
import ma.fstt.gestion_stations.repositories.CarburantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CarburantService {

    private final CarburantRepository carburantRepository;

    // CREATE
    public Carburant createCarburant(Carburant carburant) {
        if (carburantRepository.existsByType(carburant.getType())) {
            throw new RuntimeException("Un carburant avec ce type existe déjà");
        }
        return carburantRepository.save(carburant);
    }

    // READ
    public List<Carburant> getAllCarburants() {
        return carburantRepository.findAll();
    }

    public Optional<Carburant> getCarburantById(Long id) {
        return carburantRepository.findById(id);
    }

    public Optional<Carburant> getCarburantByType(String type) {
        return carburantRepository.findByType(type);
    }

    // UPDATE
    public Carburant updateCarburant(Long id, Carburant carburantDetails) {
        Carburant carburant = carburantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carburant non trouvé avec l'id : " + id));

        // Vérifier si le nouveau type existe déjà (sauf pour le carburant actuel)
        if (!carburant.getType().equals(carburantDetails.getType()) &&
                carburantRepository.existsByType(carburantDetails.getType())) {
            throw new RuntimeException("Un carburant avec ce type existe déjà");
        }

        carburant.setType(carburantDetails.getType());
        carburant.setDescription(carburantDetails.getDescription());

        return carburantRepository.save(carburant);
    }

    // DELETE
    public void deleteCarburant(Long id) {
        if (!carburantRepository.existsById(id)) {
            throw new RuntimeException("Carburant non trouvé avec l'id : " + id);
        }
        carburantRepository.deleteById(id);
    }
}