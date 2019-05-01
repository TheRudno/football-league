import { Component, OnInit } from '@angular/core';
import {MatchService} from "../../services/match.service";
import {Match} from "../../shared/match.model";


@Component({
  selector: 'app-match',
  templateUrl: './match.component.html',
  styleUrls: ['./match.component.css']
})
export class MatchComponent implements OnInit {

  private matches: Match[];

  constructor(private matchService: MatchService) { }

  ngOnInit() {

    this.matchService.getMatches().subscribe(
      data => this.matches = data,
      error => console.log(error)
    );


    // this.clubService.getClubs().subscribe(
    //   (data : Club[]) => this.clubs = data,
    //   error => { console.log(error) }
    // )
  }

}
