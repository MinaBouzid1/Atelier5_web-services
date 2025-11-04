import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, ActivatedRoute, RouterLink } from '@angular/router';
import { CarburantService } from '../../services/carburant.service';
import { Carburant } from '../../models/carburant';

@Component({
  selector: 'app-carburant-form',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './carburant-form.component.html',
  styleUrl: './carburant-form.component.css'
})
export class CarburantFormComponent implements OnInit {
  carburant: Carburant = new Carburant();
  isEditMode: boolean = false;
  carburantId?: number;

  constructor(
    private carburantService: CarburantService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.carburantId = Number(this.route.snapshot.paramMap.get('id'));

    if (this.carburantId) {
      this.isEditMode = true;
      this.loadCarburant(this.carburantId);
    }
  }

  loadCarburant(id: number): void {
    this.carburantService.getCarburantById(id).subscribe({
      next: (data) => {
        this.carburant = data;
      },
      error: (error) => {
        console.error('Erreur lors du chargement du carburant', error);
        alert('Erreur lors du chargement du carburant');
      }
    });
  }

  onSubmit(): void {
    if (this.isEditMode && this.carburantId) {
      this.carburantService.updateCarburant(this.carburantId, this.carburant).subscribe({
        next: () => {
          alert('Carburant mis à jour avec succès');
          this.router.navigate(['/carburants']);
        },
        error: (error) => {
          console.error('Erreur lors de la mise à jour', error);
          alert('Erreur lors de la mise à jour du carburant');
        }
      });
    } else {
      this.carburantService.createCarburant(this.carburant).subscribe({
        next: () => {
          alert('Carburant créé avec succès');
          this.router.navigate(['/carburants']);
        },
        error: (error) => {
          console.error('Erreur lors de la création', error);
          alert('Erreur lors de la création du carburant');
        }
      });
    }
  }
}
