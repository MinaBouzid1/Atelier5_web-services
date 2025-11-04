import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { StationListComponent } from './components/station-list/station-list.component';
import { StationFormComponent } from './components/station-form/station-form.component';
import { CarburantListComponent } from './components/carburant-list/carburant-list.component';
import { CarburantFormComponent } from './components/carburant-form/carburant-form.component';
import { PrixListComponent } from './components/prix-list/prix-list.component';
import { PrixFormComponent } from './components/prix-form/prix-form.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'stations', component: StationListComponent },
  { path: 'stations/new', component: StationFormComponent },
  { path: 'stations/edit/:id', component: StationFormComponent },
  { path: 'carburants', component: CarburantListComponent },
  { path: 'carburants/new', component: CarburantFormComponent },
  { path: 'carburants/edit/:id', component: CarburantFormComponent },
  { path: 'prix', component: PrixListComponent },
  { path: 'prix/new', component: PrixFormComponent },
  { path: 'prix/edit/:id', component: PrixFormComponent },
  { path: '**', redirectTo: '' }
];
