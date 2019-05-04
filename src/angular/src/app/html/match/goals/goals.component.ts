import {Component, Input, OnInit} from '@angular/core';
import {Goal} from '../../../shared/goal.model';
import {FootballerService} from '../../../services/footballer.service';
import {Footballer} from '../../../shared/footballer.model';
import {Match} from '../../../shared/match.model';
import {ClubService} from '../../../services/club.service';
import {GoalService} from '../../../services/goal.service';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-goals',
  templateUrl: './goals.component.html',
  styleUrls: ['./goals.component.css']
})
export class GoalsComponent implements OnInit {


  @Input()
  match: Match;

  @Input()
  homeGoals: Goal[];

  @Input()
  awayGoals: Goal[];

  public willUpdate = false;

  public footballers: Footballer[];

  private homeFootballers: Footballer[];
  private awayFootballers: Footballer[];

  public formFootballers: Footballer[] = [];

  public goalCandidate: Goal = new Goal(null, null, null, null, null);

  constructor(
    private footballerService: FootballerService,
    private clubService: ClubService,
    private goalService: GoalService,
    private toastService: ToastrService
  ) { }

  ngOnInit() {

    this.footballerService.getFootballers().subscribe(
      footballers => this.footballers = footballers,
      error => console.log(error)
    );

    this.clubService.getFootballersFromClub(this.match.homeSide).subscribe(
      data => this.homeFootballers = data,
      error => console.log(error)
    );
    this.clubService.getFootballersFromClub(this.match.awaySide).subscribe(
      data => this.awayFootballers = data,
      error => console.log(error)
    );
  }

  getFootballerName(id: number) {
    const footballer = this.footballers.find( data => {
      return data.id === id;
    });
    return footballer.name + ' ' + footballer.surname;
  }

  setGoalCandidate(side: string) {
    if (side === 'HOME') {
      this.formFootballers = this.homeFootballers;
    } else if (side === 'AWAY') {
      this.formFootballers = this.awayFootballers;
    }
    this.willUpdate = false;
    this.goalCandidate = new Goal(null, null, this.match.id, null, side);
  }

  setGoalCandidateToUpdate(goal: Goal) {
    this.willUpdate = true;
    if (goal.side === 'HOME') {
      this.formFootballers = this.homeFootballers;
    } else if (goal.side === 'AWAY') {
      this.formFootballers = this.awayFootballers;
    }
    this.goalCandidate = goal;
  }

  onSubmit() {
    if (this.willUpdate) {
      this.goalService.updateGoal(this.goalCandidate).subscribe(
        data => {
          // todo: update emitter
          this.toastService.success('Pozmieniano gole', 'Poszły łapówki');
        },
        error => this.toastService.error(error.message, 'Błąd!')
      );
    } else {
      this.goalService.addGoal(this.goalCandidate).subscribe(
        data => {
          // todo: update emitter
          this.toastService.success('Dodano gola', 'Goooool!');
        },
        error => this.toastService.error(error.message, 'Błąd!')
      );
    }
  }


  removeGoal(id: number) {
    this.goalService.removeGoal(id).subscribe(
      data => {
        // todo: update emitter
        this.toastService.success('i już nie ma gola.', '!loooooooG');
      },
      error => this.toastService.error(error.message, 'Błąd!')
    );
  }

}
