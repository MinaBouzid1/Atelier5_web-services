import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { StationService } from '../../services/station.service';
import { Station } from '../../models/station';

@Component({
  selector: 'app-station-list',
  standalone: true,
  imports: [CommonModule, RouterLink, FormsModule],
  templateUrl: './station-list.component.html',
  styleUrl: './station-list.component.css'
})
export class StationListComponent implements OnInit {
  stations: Station[] = [];
  searchTerm: string = '';
  searchType: string = 'nom';

  constructor(private stationService: StationService) { }

  ngOnInit(): void {
    this.loadStations();
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

  searchStations(): void {
    if (!this.searchTerm.trim()) {
      this.loadStations();
      return;
    }

    if (this.searchType === 'nom') {
      this.stationService.searchStationsByNom(this.searchTerm).subscribe({
        next: (data) => {
          this.stations = data;
        },
        error: (error) => {
          console.error('Erreur lors de la recherche', error);
        }
      });
    } else {
      this.stationService.searchStationsByAdresse(this.searchTerm).subscribe({
        next: (data) => {
          this.stations = data;
        },
        error: (error) => {
          console.error('Erreur lors de la recherche', error);
        }
      });
    }
  }

  deleteStation(id: number | undefined): void {
    if (!id) return;

    if (confirm('Êtes-vous sûr de vouloir supprimer cette station ?')) {
      this.stationService.deleteStation(id).subscribe({
        next: () => {
          this.loadStations();
          alert('Station supprimée avec succès');
        },
        error: (error) => {
          console.error('Erreur lors de la suppression', error);
          alert('Erreur lors de la suppression de la station');
        }
      });
    }
  }
}
