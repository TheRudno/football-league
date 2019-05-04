import { Component, OnInit } from '@angular/core';
import {Club} from '../../../shared/club.model';
import {NgForm} from '@angular/forms';
import {Match} from '../../../shared/match.model';
import {MatchService} from '../../../services/match.service';

@Component({
  selector: 'app-match-add',
  templateUrl: './match-add.component.html',
  styleUrls: ['./match-add.component.css']
})
export class MatchAddComponent implements OnInit {

  public match: Match = new Match(null, null, null, null, null, null);

  public matchResults: string[];


  constructor(private matchService: MatchService) { }

  ngOnInit() {
    this.matchService.getMatchResults().subscribe(
      value => this.matchResults = value,
      error => console.log(error)
    );

  }


  onSubmit() {
    console.log(this.match);
  }

  assignToHomeSideId( event: number) {
    this.match.homeSideId = event;
  }
  assignToAwaySideId( event: number) {
    this.match.awaySideId = event;
  }
}
