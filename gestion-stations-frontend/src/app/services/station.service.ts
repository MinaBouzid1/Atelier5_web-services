import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Station } from '../models/station';

@Injectable({
  providedIn: 'root'
})
export class StationService {
  private baseUrl = 'http://localhost:8080/api/stations';

  constructor(private http: HttpClient) { }

  getAllStations(): Observable<Station[]> {
    return this.http.get<Station[]>(this.baseUrl);
  }

  getStationById(id: number): Observable<Station> {
    return this.http.get<Station>(`${this.baseUrl}/${id}`);
  }

  createStation(station: Station): Observable<Station> {
    return this.http.post<Station>(this.baseUrl, station);
  }

  updateStation(id: number, station: Station): Observable<Station> {
    return this.http.put<Station>(`${this.baseUrl}/${id}`, station);
  }

  deleteStation(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`);
  }

  searchStationsByNom(nom: string): Observable<Station[]> {
    return this.http.get<Station[]>(`${this.baseUrl}/search/nom?nom=${nom}`);
  }

  searchStationsByAdresse(adresse: string): Observable<Station[]> {
    return this.http.get<Station[]>(`${this.baseUrl}/search/adresse?adresse=${adresse}`);
  }
}
