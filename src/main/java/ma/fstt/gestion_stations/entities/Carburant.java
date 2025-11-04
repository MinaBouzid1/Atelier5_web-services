package ma.fstt.gestion_stations.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carburants")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Carburant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String type; // Essence, Diesel, GPL, etc.

    @Column(length = 255)
    private String description;

    @OneToMany(mappedBy = "carburant", cascade = CascadeType.ALL)
    @JsonManagedReference("carburant-prix")
    private List<PrixJournalier> prixJournaliers = new ArrayList<>();
}