package ma.fstt.gestion_stations.repositories;

import ma.fstt.gestion_stations.entities.PrixJournalier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PrixJournalierRepository extends JpaRepository<PrixJournalier, Long> {

    List<PrixJournalier> findByStationId(Long stationId);

    List<PrixJournalier> findByCarburantId(Long carburantId);

    List<PrixJournalier> findByDate(LocalDate date);

    @Query("SELECT p FROM PrixJournalier p WHERE p.station.id = :stationId AND p.date = :date")
    List<PrixJournalier> findByStationIdAndDate(@Param("stationId") Long stationId,
                                                @Param("date") LocalDate date);

    @Query("SELECT p FROM PrixJournalier p WHERE p.station.id = :stationId AND p.carburant.id = :carburantId AND p.date = :date")
    Optional<PrixJournalier> findByStationIdAndCarburantIdAndDate(
            @Param("stationId") Long stationId,
            @Param("carburantId") Long carburantId,
            @Param("date") LocalDate date);

    @Query("SELECT p FROM PrixJournalier p WHERE p.station.id = :stationId AND p.carburant.id = :carburantId ORDER BY p.date DESC")
    List<PrixJournalier> findByStationIdAndCarburantIdOrderByDateDesc(
            @Param("stationId") Long stationId,
            @Param("carburantId") Long carburantId);
}