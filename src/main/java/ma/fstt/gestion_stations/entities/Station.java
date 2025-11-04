package ma.fstt.gestion_stations.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "stations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nom;

    @Column(nullable = false)
    private String adresse;

    @Column(length = 20)
    private String telephone;

    private Double latitude;
    private Double longitude;

    @OneToMany(mappedBy = "station", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("station-prix")
    private List<PrixJournalier> prixJournaliers = new ArrayList<>();

    // Méthodes utilitaires
    public void addPrixJournalier(PrixJournalier prix) {
        prixJournaliers.add(prix);
        prix.setStation(this);
    }

    public void removePrixJournalier(PrixJournalier prix) {
        prixJournaliers.remove(prix);
        prix.setStation(null);
    }
}