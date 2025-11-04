package ma.fstt.gestion_stations.repositories;

import ma.fstt.gestion_stations.entities.Carburant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarburantRepository extends JpaRepository<Carburant, Long> {
    Optional<Carburant> findByType(String type);
    boolean existsByType(String type);
}