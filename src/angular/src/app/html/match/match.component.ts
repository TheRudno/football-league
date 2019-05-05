import { Component, OnInit } from '@angular/core';
import {MatchService} from '../../services/match.service';
import {Match} from '../../shared/match.model';
import {ClubService} from '../../services/club.service';
import {Club} from '../../shared/club.model';
import {Goal} from '../../shared/goal.model';
import {GoalService} from '../../services/goal.service';
import {UpdateEmitterService} from "../../services/update-emitter.service";


@Component({
  selector: 'app-match',
  templateUrl: './match.component.html',
  styleUrls: ['./match.component.css']
})
export class MatchComponent implements OnInit {

  public matches: Match[];
  private clubs: Club[];
  private goals: Goal[];

  constructor(
    private matchService: MatchService,
    private clubService: ClubService,
    private goalService: GoalService,
    private updater: UpdateEmitterService
  ) { }

  ngOnInit() {

    this.matchService.getMatches().subscribe(
      matches => {
        this.clubService.getClubs().subscribe(
          clubs => {
            this.goalService.getGoals().subscribe(
              goals => {
                this.clubs = clubs;
                this.goals = goals;
                this.matches = matches;
              },
              error => console.log(error)
            );
          },
          error => console.log(error)
        );
      },
      error => console.log(error)
    );

    this.updater.matchesUpdate.subscribe(
      e => {
        this.matchService.getMatches().subscribe(
          matches => {
            this.clubService.getClubs().subscribe(
              clubs => {
                this.goalService.getGoals().subscribe(
                  goals => {
                    this.clubs = clubs;
                    this.goals = goals;
                    this.matches = matches;
                  },
                  error => console.log(error)
                );
              },
              error => console.log(error)
            );
          },
          error => console.log(error)
        );
      }
    );

  }


  getClub(id: number) {
    return this.clubs.find( club => {
      return club.id === id;
    });
  }

  getGoal(id: number) {
    return this.goals.find( goal => {
      return goal.id === id;
    });
  }


  getGoals(match: Match) {
    const result = {AWAY: [], HOME: []};

    for (const x of match.goals) {
      const goal = this.getGoal(x);
      result[goal.side].push(goal);
    }
    return result;
  }


  getResultString(match: Match) {
    const result = this.getGoals(match);
    return result.HOME.length + ':' + result.AWAY.length;
  }



}
