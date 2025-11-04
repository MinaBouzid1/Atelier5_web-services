import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PrixJournalier } from '../models/prix-journalier';

@Injectable({
  providedIn: 'root'
})
export class PrixJournalierService {
  private baseUrl = 'http://localhost:8080/api/prix';

  constructor(private http: HttpClient) { }

  getAllPrix(): Observable<PrixJournalier[]> {
    return this.http.get<PrixJournalier[]>(this.baseUrl);
  }

  getPrixById(id: number): Observable<PrixJournalier> {
    return this.http.get<PrixJournalier>(`${this.baseUrl}/${id}`);
  }

  createPrix(stationId: number, carburantId: number, prix: PrixJournalier): Observable<PrixJournalier> {
    return this.http.post<PrixJournalier>(
      `${this.baseUrl}/station/${stationId}/carburant/${carburantId}`,
      prix
    );
  }

  updatePrix(id: number, prix: PrixJournalier): Observable<PrixJournalier> {
    return this.http.put<PrixJournalier>(`${this.baseUrl}/${id}`, prix);
  }

  deletePrix(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`);
  }

  getPrixByStation(stationId: number): Observable<PrixJournalier[]> {
    return this.http.get<PrixJournalier[]>(`${this.baseUrl}/station/${stationId}`);
  }

  getPrixByStationAndDate(stationId: number, date: string): Observable<PrixJournalier[]> {
    return this.http.get<PrixJournalier[]>(`${this.baseUrl}/station/${stationId}/date/${date}`);
  }

  getHistoriquePrix(stationId: number, carburantId: number): Observable<PrixJournalier[]> {
    return this.http.get<PrixJournalier[]>(
      `${this.baseUrl}/historique/station/${stationId}/carburant/${carburantId}`
    );
  }
}
