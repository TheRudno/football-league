import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Match} from '../../../shared/match.model';
import {MatchService} from '../../../services/match.service';
import {ClubService} from '../../../services/club.service';
import {GoalService} from '../../../services/goal.service';
import {Club} from '../../../shared/club.model';
import {Goal} from '../../../shared/goal.model';
import {ToastrService} from "ngx-toastr";
import {UpdateEmitterService} from "../../../services/update-emitter.service";

@Component({
  selector: 'app-match-overview',
  templateUrl: './match-overview.component.html',
  styleUrls: ['./match-overview.component.css']
})
export class MatchOverviewComponent implements OnInit {

  public match: Match = new Match(null, null, null, null, null, null);
  public goals: Goal[];


  public homeClub: Club;
  public awayClub: Club;

  public matchGoals = {HOME: [], AWAY: []};


  constructor(
    private route: ActivatedRoute,
    private matchService: MatchService,
    private clubService: ClubService,
    private goalService: GoalService,
    private toastService: ToastrService,
    private updaterService: UpdateEmitterService

  ) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      if (params.id != null) {
        this.matchService.getMatch(params.id).subscribe(
          match => {
            this.clubService.getClubs().subscribe(
              clubs => {
                this.goalService.getGoals().subscribe(
                  goals => {
                    this.homeClub = clubs.find( club => {
                      return club.id === match.homeSide;
                    });

                    this.awayClub = clubs.find( club => {
                      return club.id === match.awaySide;
                    });

                    this.goals = goals;
                    this.matchGoals = this.getGoals(match);

                    this.match = match;
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
    });

    this.updaterService.goalsUpdate.subscribe(
      () => {
        this.route.params.subscribe(params => {
          if (params.id != null) {
            this.matchService.getMatch(params.id).subscribe(
              match => {
                this.clubService.getClubs().subscribe(
                  clubs => {
                    this.goalService.getGoals().subscribe(
                      goals => {
                        this.homeClub = clubs.find( club => {
                          return club.id === match.homeSide;
                        });

                        this.awayClub = clubs.find( club => {
                          return club.id === match.awaySide;
                        });

                        this.goals = goals;
                        this.matchGoals = this.getGoals(match);

                        this.match = match;
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
        });
      }
    );


  }

  getGoals(match: Match) {
    const result = {AWAY: [], HOME: []};

    for (const x of match.goals) {
      const goal = this.goals.find(data => {
        return data.id === x;
      });
      result[goal.side].push(goal);
    }
    result.AWAY.sort((a, b) => (a.goalMinute - b.goalMinute));
    result.HOME.sort((a, b) => (a.goalMinute - b.goalMinute));
    return result;
  }

  getResultString() {
    return this.matchGoals.HOME.length + ':' + this.matchGoals.AWAY.length;
  }

  removeMatch() {
    this.matchService.removeMatch(this.match.id).subscribe(
      answer => {
        this.toastService.success("Usunięto","Wszystko ok");
        this.updaterService.updateMatches();
      },
      error => this.toastService.error(error,"To nie zadziałało")
    );
  }


}
