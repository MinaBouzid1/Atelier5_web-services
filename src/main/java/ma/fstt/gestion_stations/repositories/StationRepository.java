package ma.fstt.gestion_stations.repositories;

import ma.fstt.gestion_stations.entities.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {
    List<Station> findByNomContainingIgnoreCase(String nom);
    List<Station> findByAdresseContainingIgnoreCase(String adresse);
}