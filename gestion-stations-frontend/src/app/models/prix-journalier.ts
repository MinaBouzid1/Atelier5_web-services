import { Station } from './station';
import { Carburant } from './carburant';

export class PrixJournalier {
  id?: number;
  station?: Station;
  carburant?: Carburant;
  date: string = '';
  prix: number = 0;
  remarque?: string;
}
