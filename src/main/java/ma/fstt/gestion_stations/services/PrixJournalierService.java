package ma.fstt.gestion_stations.services;

import ma.fstt.gestion_stations.entities.Carburant;
import ma.fstt.gestion_stations.entities.PrixJournalier;
import ma.fstt.gestion_stations.entities.Station;
import ma.fstt.gestion_stations.repositories.CarburantRepository;
import ma.fstt.gestion_stations.repositories.PrixJournalierRepository;
import ma.fstt.gestion_stations.repositories.StationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PrixJournalierService {

    private final PrixJournalierRepository prixJournalierRepository;
    private final StationRepository stationRepository;
    private final CarburantRepository carburantRepository;

    // CREATE
    public PrixJournalier createPrixJournalier(Long stationId, Long carburantId, PrixJournalier prixJournalier) {
        Station station = stationRepository.findById(stationId)
                .orElseThrow(() -> new RuntimeException("Station non trouvée avec l'id : " + stationId));

        Carburant carburant = carburantRepository.findById(carburantId)
                .orElseThrow(() -> new RuntimeException("Carburant non trouvé avec l'id : " + carburantId));

        // Vérifier si un prix existe déjà pour cette combinaison
        Optional<PrixJournalier> existingPrix = prixJournalierRepository
                .findByStationIdAndCarburantIdAndDate(stationId, carburantId, prixJournalier.getDate());

        if (existingPrix.isPresent()) {
            throw new RuntimeException("Un prix existe déjà pour cette station, ce carburant et cette date");
        }

        prixJournalier.setStation(station);
        prixJournalier.setCarburant(carburant);

        return prixJournalierRepository.save(prixJournalier);
    }

    // READ
    public List<PrixJournalier> getAllPrixJournaliers() {
        return prixJournalierRepository.findAll();
    }

    public Optional<PrixJournalier> getPrixJournalierById(Long id) {
        return prixJournalierRepository.findById(id);
    }

    public List<PrixJournalier> getPrixByStation(Long stationId) {
        return prixJournalierRepository.findByStationId(stationId);
    }

    public List<PrixJournalier> getPrixByStationAndDate(Long stationId, LocalDate date) {
        return prixJournalierRepository.findByStationIdAndDate(stationId, date);
    }

    public List<PrixJournalier> getPrixByCarburant(Long carburantId) {
        return prixJournalierRepository.findByCarburantId(carburantId);
    }

    public List<PrixJournalier> getHistoriquePrix(Long stationId, Long carburantId) {
        return prixJournalierRepository.findByStationIdAndCarburantIdOrderByDateDesc(stationId, carburantId);
    }

    // UPDATE
    public PrixJournalier updatePrixJournalier(Long id, PrixJournalier prixDetails) {
        PrixJournalier prix = prixJournalierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prix journalier non trouvé avec l'id : " + id));

        prix.setPrix(prixDetails.getPrix());
        prix.setRemarque(prixDetails.getRemarque());
        prix.setDate(prixDetails.getDate());

        return prixJournalierRepository.save(prix);
    }

    // DELETE
    public void deletePrixJournalier(Long id) {
        if (!prixJournalierRepository.existsById(id)) {
            throw new RuntimeException("Prix journalier non trouvé avec l'id : " + id);
        }
        prixJournalierRepository.deleteById(id);
    }
}