import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, ActivatedRoute, RouterLink } from '@angular/router';
import { PrixJournalierService } from '../../services/prix-journalier.service';
import { StationService } from '../../services/station.service';
import { CarburantService } from '../../services/carburant.service';
import { PrixJournalier } from '../../models/prix-journalier';
import { Station } from '../../models/station';
import { Carburant } from '../../models/carburant';

@Component({
  selector: 'app-prix-form',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './prix-form.component.html',
  styleUrl: './prix-form.component.css'
})
export class PrixFormComponent implements OnInit {
  prix: PrixJournalier = new PrixJournalier();
  stations: Station[] = [];
  carburants: Carburant[] = [];
  isEditMode: boolean = false;
  prixId?: number;
  selectedStationId?: number;
  selectedCarburantId?: number;

  constructor(
    private prixService: PrixJournalierService,
    private stationService: StationService,
    private carburantService: CarburantService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.prixId = Number(this.route.snapshot.paramMap.get('id'));
    this.loadStations();
    this.loadCarburants();

    if (this.prixId) {
      this.isEditMode = true;
      this.loadPrix(this.prixId);
    } else {
      // Set today's date by default
      const today = new Date();
      this.prix.date = today.toISOString().split('T')[0];
    }
  }

  loadStations(): void {
    this.stationService.getAllStations().subscribe({
      next: (data) => {
        this.stations = data;
      },
      error: (error) => {
        console.error('Erreur lors du chargement des stations', error);
      }
    });
  }

  loadCarburants(): void {
    this.carburantService.getAllCarburants().subscribe({
      next: (data) => {
        this.carburants = data;
      },
      error: (error) => {
        console.error('Erreur lors du chargement des carburants', error);
      }
    });
  }

  loadPrix(id: number): void {
    this.prixService.getPrixById(id).subscribe({
      next: (data) => {
        this.prix = data;
        this.selectedStationId = data.station?.id;
        this.selectedCarburantId = data.carburant?.id;
      },
      error: (error) => {
        console.error('Erreur lors du chargement du prix', error);
        alert('Erreur lors du chargement du prix');
      }
    });
  }

  onSubmit(): void {
    if (this.isEditMode && this.prixId) {
      this.prixService.updatePrix(this.prixId, this.prix).subscribe({
        next: () => {
          alert('Prix mis à jour avec succès');
          this.router.navigate(['/prix']);
        },
        error: (error) => {
          console.error('Erreur lors de la mise à jour', error);
          alert('Erreur lors de la mise à jour du prix');
        }
      });
    } else {
      if (!this.selectedStationId || !this.selectedCarburantId) {
        alert('Veuillez sélectionner une station et un carburant');
        return;
      }

      this.prixService.createPrix(this.selectedStationId, this.selectedCarburantId, this.prix).subscribe({
        next: () => {
          alert('Prix créé avec succès');
          this.router.navigate(['/prix']);
        },
        error: (error) => {
          console.error('Erreur lors de la création', error);
          alert('Erreur lors de la création du prix. Vérifiez qu\'un prix n\'existe pas déjà pour cette combinaison.');
        }
      });
    }
  }
}
