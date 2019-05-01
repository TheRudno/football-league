import { Injectable } from '@angular/core';
import { Adapter } from '../services/adapter';

export class Match {
  constructor(
    public id: number,
    public homeSideId: number,
    public awaySideId: number,
    public matchDate: Date,
    public goalIds: number[],
    public result: string
  ) {}
}

@Injectable({
  providedIn: 'root'
})
export class MatchAdapter implements Adapter<Match> {
  adapt(from: any){
    return new Match(
      from.id,
      from.homeSide,
      from.awaySide,
      from.matchDate,
      from.goals,
      from.result
    );
  }
}
