import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, ActivatedRoute, RouterLink } from '@angular/router';
import { StationService } from '../../services/station.service';
import { Station } from '../../models/station';

@Component({
  selector: 'app-station-form',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './station-form.component.html',
  styleUrl: './station-form.component.css'
})
export class StationFormComponent implements OnInit {
  station: Station = new Station();
  isEditMode: boolean = false;
  stationId?: number;

  constructor(
    private stationService: StationService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.stationId = Number(this.route.snapshot.paramMap.get('id'));

    if (this.stationId) {
      this.isEditMode = true;
      this.loadStation(this.stationId);
    }
  }

  loadStation(id: number): void {
    this.stationService.getStationById(id).subscribe({
      next: (data) => {
        this.station = data;
      },
      error: (error) => {
        console.error('Erreur lors du chargement de la station', error);
        alert('Erreur lors du chargement de la station');
      }
    });
  }

  onSubmit(): void {
    if (this.isEditMode && this.stationId) {
      this.stationService.updateStation(this.stationId, this.station).subscribe({
        next: () => {
          alert('Station mise à jour avec succès');
          this.router.navigate(['/stations']);
        },
        error: (error) => {
          console.error('Erreur lors de la mise à jour', error);
          alert('Erreur lors de la mise à jour de la station');
        }
      });
    } else {
      this.stationService.createStation(this.station).subscribe({
        next: () => {
          alert('Station créée avec succès');
          this.router.navigate(['/stations']);
        },
        error: (error) => {
          console.error('Erreur lors de la création', error);
          alert('Erreur lors de la création de la station');
        }
      });
    }
  }
}
