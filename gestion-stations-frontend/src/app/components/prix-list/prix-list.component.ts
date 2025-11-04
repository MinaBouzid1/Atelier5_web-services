import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { PrixJournalierService } from '../../services/prix-journalier.service';
import { StationService } from '../../services/station.service';
import { PrixJournalier } from '../../models/prix-journalier';
import { Station } from '../../models/station';

@Component({
  selector: 'app-prix-list',
  standalone: true,
  imports: [CommonModule, RouterLink, FormsModule],
  templateUrl: './prix-list.component.html',
  styleUrl: './prix-list.component.css'
})
export class PrixListComponent implements OnInit {
  prix: PrixJournalier[] = [];
  stations: Station[] = [];
  selectedStationId?: number;
  selectedDate?: string;

  constructor(
    private prixService: PrixJournalierService,
    private stationService: StationService
  ) { }

  ngOnInit(): void {
    this.loadPrix();
    this.loadStations();
  }

  loadPrix(): void {
    this.prixService.getAllPrix().subscribe({
      next: (data) => {
        this.prix = data;
      },
      error: (error) => {
        console.error('Erreur lors du chargement des prix', error);
      }
    });
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

  filterPrix(): void {
    if (!this.selectedStationId && !this.selectedDate) {
      this.loadPrix();
      return;
    }

    if (this.selectedStationId && this.selectedDate) {
      this.prixService.getPrixByStationAndDate(this.selectedStationId, this.selectedDate).subscribe({
        next: (data) => {
          this.prix = data;
        },
        error: (error) => {
          console.error('Erreur lors du filtrage', error);
        }
      });
    } else if (this.selectedStationId) {
      this.prixService.getPrixByStation(this.selectedStationId).subscribe({
        next: (data) => {
          this.prix = data;
        },
        error: (error) => {
          console.error('Erreur lors du filtrage', error);
        }
      });
    }
  }

  resetFilter(): void {
    this.selectedStationId = undefined;
    this.selectedDate = undefined;
    this.loadPrix();
  }

  deletePrix(id: number | undefined): void {
    if (!id) return;

    if (confirm('Êtes-vous sûr de vouloir supprimer ce prix ?')) {
      this.prixService.deletePrix(id).subscribe({
        next: () => {
          this.loadPrix();
          alert('Prix supprimé avec succès');
        },
        error: (error) => {
          console.error('Erreur lors de la suppression', error);
          alert('Erreur lors de la suppression du prix');
        }
      });
    }
  }
}
