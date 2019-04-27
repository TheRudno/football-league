import { Injectable } from '@angular/core';
import { Adapter } from "../services/adapter";

export class Goal {
  constructor() {}
}

@Injectable({
  providedIn: 'root'
})
export class GoalAdapter implements Adapter<Goal> {
  adapt(){
    return null;
  }
}
