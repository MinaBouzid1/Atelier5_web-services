package ma.fstt.gestion_stations.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "prix_journaliers",
        uniqueConstraints = @UniqueConstraint(columnNames = {"station_id", "carburant_id", "date"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrixJournalier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "station_id", nullable = false)
    @JsonBackReference("station-prix")
    private Station station;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carburant_id", nullable = false)
    @JsonBackReference("carburant-prix")
    private Carburant carburant;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private Double prix;

    @Column(length = 255)
    private String remarque;
}