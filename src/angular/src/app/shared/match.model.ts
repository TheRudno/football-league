import { Injectable } from '@angular/core';
import { Adapter } from "../services/adapter";

export class Match {
  constructor() {}
}

@Injectable({
  providedIn: 'root'
})
export class MatchAdapter implements Adapter<Match> {
  adapt(){
    return null;
  }
}
