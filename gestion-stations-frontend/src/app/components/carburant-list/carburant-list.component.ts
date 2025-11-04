import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { CarburantService } from '../../services/carburant.service';
import { Carburant } from '../../models/carburant';

@Component({
  selector: 'app-carburant-list',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './carburant-list.component.html',
  styleUrl: './carburant-list.component.css'
})
export class CarburantListComponent implements OnInit {
  carburants: Carburant[] = [];

  constructor(private carburantService: CarburantService) { }

  ngOnInit(): void {
    this.loadCarburants();
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

  deleteCarburant(id: number | undefined): void {
    if (!id) return;

    if (confirm('Êtes-vous sûr de vouloir supprimer ce carburant ?')) {
      this.carburantService.deleteCarburant(id).subscribe({
        next: () => {
          this.loadCarburants();
          alert('Carburant supprimé avec succès');
        },
        error: (error) => {
          console.error('Erreur lors de la suppression', error);
          alert('Erreur lors de la suppression du carburant');
        }
      });
    }
  }
}
