import {Component, Input, OnInit} from '@angular/core';
import {Club} from '../../../shared/club.model';
import {NgForm} from '@angular/forms';
import {Match} from '../../../shared/match.model';
import {MatchService} from '../../../services/match.service';
import {ToastrService} from 'ngx-toastr';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-match-add',
  templateUrl: './match-edit.component.html',
  styleUrls: ['./match-edit.component.css']
})
export class MatchEditComponent implements OnInit {

  public match: Match = new Match(null, null, null, null, null, null);

  public matchResults: string[];

  public addMode;


  constructor(
    private matchService: MatchService,
    private toastService: ToastrService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.matchService.getMatchResults().subscribe(
      value => this.matchResults = value,
      error => console.log(error)
    );
    this.addMode = this.router.url.includes('add');

    if (!this.addMode) {
      this.route.params.subscribe(params => {
        if (params.id != null) {
          this.matchService.getMatch(params.id).subscribe(data => {
              console.log(data);
              this.match = data;
            },
            error => console.log(error));
        }
      });
    }
  }

  onSubmit() {
    if (this.match.result === '') {
      this.match.result = null;
    }
    if (this.addMode) {
      this.matchService.addMatch(this.match).subscribe(
        data => {
          // update emiter
          this.toastService.success('Dodano mecz', 'Śmiga jak rakieta!');
          this.router.navigate(['..'], {relativeTo: this.route});
        },
        error => this.toastService.error(error.message, 'Błąd!')
      );
    } else {
      console.log(this.match);
      this.matchService.updateMatch(this.match).subscribe(
        data => {
          // update emiter

          this.toastService.success('Zmodyfikowano mecz', 'Śmiga jak szerszeń!');
          this.router.navigate(['..'], {relativeTo: this.route});
        },
        error => this.toastService.error(error.message, 'Błąd!')
      );
    }
  }

  assignToHomeSideId( event: number) {
    this.match.homeSide = event;
  }
  assignToAwaySideId( event: number) {
    this.match.awaySide = event;
  }
}
