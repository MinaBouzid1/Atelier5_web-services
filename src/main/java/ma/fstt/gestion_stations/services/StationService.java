package ma.fstt.gestion_stations.services;

import ma.fstt.gestion_stations.entities.Station;
import ma.fstt.gestion_stations.repositories.StationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class StationService {

    private final StationRepository stationRepository;

    // CREATE
    public Station createStation(Station station) {
        return stationRepository.save(station);
    }

    // READ
    public List<Station> getAllStations() {
        return stationRepository.findAll();
    }

    public Optional<Station> getStationById(Long id) {
        return stationRepository.findById(id);
    }

    public List<Station> searchStationsByNom(String nom) {
        return stationRepository.findByNomContainingIgnoreCase(nom);
    }

    public List<Station> searchStationsByAdresse(String adresse) {
        return stationRepository.findByAdresseContainingIgnoreCase(adresse);
    }

    // UPDATE
    public Station updateStation(Long id, Station stationDetails) {
        Station station = stationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Station non trouvée avec l'id : " + id));

        station.setNom(stationDetails.getNom());
        station.setAdresse(stationDetails.getAdresse());
        station.setTelephone(stationDetails.getTelephone());
        station.setLatitude(stationDetails.getLatitude());
        station.setLongitude(stationDetails.getLongitude());

        return stationRepository.save(station);
    }

    // DELETE
    public void deleteStation(Long id) {
        if (!stationRepository.existsById(id)) {
            throw new RuntimeException("Station non trouvée avec l'id : " + id);
        }
        stationRepository.deleteById(id);
    }
}